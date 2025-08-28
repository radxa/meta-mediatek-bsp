inherit image-artifact-names

IMAGE_CMD_TAR = "tar --xattrs --xattrs-include=*"
CONVERSION_CMD:tar = "touch ${IMGDEPLOYDIR}/${IMAGE_NAME}.${type}; ${IMAGE_CMD_TAR} --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}.${type}.tar -C ${TAR_IMAGE_ROOTFS} . || [ $? -eq 1 ]"
CONVERSIONTYPES:append = " tar"

AIOTFLASH_SYSROOT = "${WORKDIR}/tmp-aiotflash"
do_image_aiotflash[dirs] = "${AIOTFLASH_SYSROOT}"
do_image_aiotflash[cleandirs] = "${AIOTFLASH_SYSROOT}"
TAR_IMAGE_ROOTFS:task-image-aiotflash = "${AIOTFLASH_SYSROOT}"
IMAGE_TYPEDEP:aiotflash = "wic.img"
do_image_aiotflash[depends] += "rity-tools:do_deploy \
                                virtual/bootloader:do_deploy \
                                trusted-firmware-a:do_deploy \
                                virtual/lk:do_deploy \
                                jq-native:do_populate_sysroot \
                                ${@bb.utils.contains('MACHINE_FEATURES', 'nor-boot', 'firmware-part:do_deploy', '', d)} \
"

DEPENDS += "dtc-native"

check_bl31_size () {
	BL31_LD_PATH="${DEPLOY_DIR_IMAGE}/bl31.ld"

	for p in ${KERNEL_DEVICETREE}; do
		DTB="${DEPLOY_DIR_IMAGE}/`basename $p`"

		# Compute bl31 size
		BL31_LENGTH=`grep -o -P '(?<=, LENGTH = ).*(?=$)' ${BL31_LD_PATH}`
		BL31_LENGTH=`python3 -c "print(${BL31_LENGTH} + 0x1000)"`

		# Parse Kernel reserved size for bl31
		KERNEL_BL31_RESERVED_SIZE=`fdtget ${DTB} /reserved-memory/secmon reg | awk '{print $4}'`

		if [ ${KERNEL_BL31_RESERVED_SIZE} -lt ${BL31_LENGTH} ]; then
			bbfatal "Kernel does not reserve enough memory for bl31 (${KERNEL_BL31_RESERVED_SIZE} bytes < ${BL31_LENGTH} bytes)," \
				"increase it in \"secmon\" Kernel's DT node!"
		fi
	done
}

IMAGE_CMD:aiotflash () {
    check_bl31_size

    tmp_pack_dir=${AIOTFLASH_SYSROOT}/${IMAGE_NAME}
    mkdir -p ${tmp_pack_dir}
    cp -aL ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.manifest ${tmp_pack_dir}
    cp -aL ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.testdata.json ${tmp_pack_dir}
    cp -aL ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.wic.img ${tmp_pack_dir}
    cp -a ${DEPLOY_DIR_IMAGE}/rity.json ${tmp_pack_dir}
    cp -a ${DEPLOY_DIR_IMAGE}/partitions.json ${tmp_pack_dir}
    cp -a ${DEPLOY_DIR_IMAGE}/lk.bin ${tmp_pack_dir}

    if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        cp -a ${DEPLOY_DIR_IMAGE}/efuse.cfg ${tmp_pack_dir}
    fi

    if [ "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        cp -a ${DEPLOY_DIR_IMAGE}/lk.sign ${tmp_pack_dir}
        cp -a ${DEPLOY_DIR_IMAGE}/auth_sv5.auth ${tmp_pack_dir}
    fi

    for f in `cat ${tmp_pack_dir}/rity.json | jq -r ".partitions[]|strings"`; do
        # When "u-boot-env.bin" is requested, we should pack "u-boot-initial-env"
        if [ ${f} = "u-boot-env.bin" ]; then
            f="u-boot-initial-env"
        fi

        # The requested binary is either in the deploy or the image staging dir
        cp -aL ${DEPLOY_DIR_IMAGE}/${f} ${tmp_pack_dir} || cp -aL ${IMGDEPLOYDIR}/${f} ${tmp_pack_dir}
    done
}
