require trusted-firmware-a-mtk_2.6.inc

PROVIDES = "virtual/bl2"

DEPENDS:append = "${@oe.utils.conditional("BL2_SIGN_ENABLE", "1", " mtk-secure-boot-tools-native mtk-secure-boot-config", "", d)}"

TFA_BUILD_TARGET = "bl2"

FWUPDATE_TFA = "bl2.cap"

FWUPDATE_TFA_ARGS = " \
	--private-key ${DEPLOY_DIR_IMAGE}/u-boot-cap.key --certificate ${DEPLOY_DIR_IMAGE}/u-boot-cap.crt \
	--monotonic-count 1 --instance 0 --index 1 --guid ${FWUPDATE_TFA_ID} ${B}/bl2.img ${DEPLOYDIR}/${FWUPDATE_TFA} \
"

SIGN_EN_ARGS:mt8395 = "-s 0"
SIGN_EN_ARGS:mt8390 = "-s 1"
SIGN_EN_ARGS:mt8370 = "-s 1"
SIGN_EN_ARGS:mt8365 = "-s 0"

do_gen_bl2_img() {
	media="emmc"
	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'ufs-boot', 'ufs-boot', '', d)}" = "ufs-boot" ]; then
		media="ufs"
	fi

	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'nor-boot', 'nor-boot', '', d)}" = "nor-boot" ]; then
		media="nor"
	fi

	if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
		cp ${B}/${TFA_PLATFORM}/release/bl2.bin ${B}/bl2.img
		python3 -m sign-image_v2.pbp \
				-i ${RECIPE_SYSROOT}/${sysconfdir}/secure/key.ini \
				-g ${RECIPE_SYSROOT}/${sysconfdir}/secure/pl_gfh_config_pss.ini \
				-func sign -o ${B}/bl2.img ${B}/bl2.img ${SIGN_EN_ARGS}
		python3 -m secure_chip_tools.dev-info-hdr-tool $media ${B}/bl2.img ${B}/bl2.img
		python3 -m sign-image_v2.pbp \
				-j ${RECIPE_SYSROOT}/${sysconfdir}/secure/sbc.pem -func keyhash_pss -o ${DEPLOYDIR}/secure/keyhash ${SIGN_EN_ARGS}
	else
		cp ${B}/${TFA_PLATFORM}/release/bl2.bin ${B}/bl2.img.tmp
		truncate -s%4 ${B}/bl2.img.tmp
		uboot-mkimage -T mtk_image -a ${SOC_SRAM_ADDR} -e ${SOC_SRAM_ADDR} -n "media=$media;arm64=1" \
				-d ${B}/bl2.img.tmp ${B}/bl2.img
	fi
}

do_deploy() {
	install -m 0644 ${B}/bl2.img ${DEPLOYDIR}/
	if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
		install -m 0644 ${RECIPE_SYSROOT}/${sysconfdir}/secure/efuse.cfg ${DEPLOYDIR}/
	fi

	if [ "${@bb.utils.contains("DISTRO_FEATURES", "fwupdate", "1", "0", d)}" = "1" ]; then
		if [ "${@oe.utils.conditional("MACHINE", "genio-1200-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="a4a60c91-ffa3-4cb8-9b2a-4ff813620d22"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-1200-evk-ufs", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="dcae4ff5-53f2-4664-b059-83eb33abf06d"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-1200-evk-norboot-ufs", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="d5b2cad8-7517-4146-b6ed-aacd73841eab"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-700-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="a2c34f52-9452-4f6b-83db-fde21853e2a5"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-700-evk-norboot", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="6acd6552-f05d-4d47-bde2-b84c60129ad0"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-510-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="546af9d8-91e7-4de0-a8a3-351bf1569d64"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-510-evk-norboot", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="4889d6c8-7986-469f-bdcb-e6d303b56cbd"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-350-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_TFA_ID="221ccce5-f62a-4962-b941-ef74f306362e"
		fi

		if [ -z "${FWUPDATE_TFA_ID}" ]; then
			bbwarn "FWUPDATE_TFA_ID is not defined, it can not support fwupdate."
		else
			mkeficapsule ${FWUPDATE_TFA_ARGS}
		fi
	fi
}

python() {
    bb.build.addtask('do_gen_bl2_img', 'do_install', 'do_compile', d)
}
