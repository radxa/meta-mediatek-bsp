require recipes-bsp/trusted-firmware-a/trusted-firmware-a.inc

inherit deploy
inherit python3native

do_deploy[nostamp] = "1"

# Make sure that bl2 is built and installed before we deploy the fip
do_deploy[depends] += "virtual/bl2:do_deploy"

DEPENDS = "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "libdram", "libdram-prebuilt", d)}"
DEPENDS:append = " libbase-prebuilt"
COMPATIBLE_MACHINE = "mt*"

OVERRIDES:append = "${@bb.utils.contains("TFA_BUILD_TARGET", "bl31", ":bl31", "", d)}"

# Use TF-A for version
SRCREV_FORMAT = "tfa"

TFA_UBOOT = "1"

DEPENDS:append = " coreutils-native u-boot-tools-native"

EXTRA_OEMAKE += "E=0"
EXTRA_OEMAKE += "NEED_BL33=yes"
EXTRA_OEMAKE += "LIBDRAM=${STAGING_DIR_TARGET}/${libdir}/libdram.a"
EXTRA_OEMAKE += "LIBBASE=${STAGING_DIR_TARGET}/${libdir}/libbase.a"

EXTRA_OEMAKE:append = " \
	${@bb.utils.contains("MACHINE_FEATURES", "ufs-boot", "STORAGE_UFS=1", "", d)} \
	${@bb.utils.contains("AB_FWUPDATE_ENABLE", "1", "PLAT_AB_BOOT_ENABLE=1", "", d)} \
"
EXTRA_OEMAKE:append:mt8370 = " PLAT_HW_CRYPTO=1"
EXTRA_OEMAKE:append:mt8390 = " PLAT_HW_CRYPTO=1"
EXTRA_OEMAKE:append:mt8395 = " PLAT_HW_CRYPTO=1"

PACKAGECONFIG[huk_efuse_hwid] = "HUK_EFUSE_HWID=1"
EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"

# OP-TEE
DEPENDS:append = " ${@bb.utils.contains("MACHINE_FEATURES", "optee", "virtual/optee-os", "", d)}"
TFA_SPD = "${@bb.utils.contains("MACHINE_FEATURES", "optee", "opteed", "", d)}"
EXTRA_OEMAKE:append = " \
	BL32=${STAGING_DIR_TARGET}/${nonarch_base_libdir}/firmware/tee.bin \
	NEED_BL32=${@bb.utils.contains("MACHINE_FEATURES", "optee", "yes", "no", d)} \
	CFLAGS+=${@bb.utils.contains("MACHINE_FEATURES", "optee", "-DNEED_BL32", "", d)} \
"

S = "${WORKDIR}/git"

SRC_URI:remove = " \
    file://0002-pmf.h-made-PMF_STOTE_ENABLE-pass-Wtautological.patch  \
    file://0003-xlat-tables-v2-remove-tautological-assert.patch \
"

SRC_URI += "file://rot_key.pem"

LIC_FILES_CHKSUM += "file://docs/license.rst;md5=b2c740efedc159745b9b31f88ff03dde"

# mbed TLS v2.26.0
SRC_URI_MBEDTLS = "git://github.com/ARMmbed/mbedtls.git;name=mbedtls;protocol=https;destsuffix=git/mbedtls;branch=master"
SRCREV_mbedtls = "e483a77c85e1f9c1dd2eb1c5a8f552d2617fe400"

LIC_FILES_CHKSUM_MBEDTLS = "file://mbedtls/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

TFA_BUILD_TARGET = "bl31 fip"

#
# Secure Boot
#
TFA_SECURE_BOOT_OPTION = " \
	TRUSTED_BOARD_BOOT=1 \
	GENERATE_COT=1 \
	ROT_KEY=${SECURE_BOOT_ROT_KEY} \
"

EXTRA_OEMAKE += " \
	${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "${TFA_SECURE_BOOT_OPTION}", "", d)} \
"

TFA_MBEDTLS = " \
	${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "1", "", d)} \
"

DEFAULT_ROT_KEY = "${WORKDIR}/rot_key.pem"
SECURE_BOOT_ROT_KEY ?= "${DEFAULT_ROT_KEY}"

FWUPDATE_FIP = "fip.cap"

FWUPDATE_FIP_ARGS = " \
	--private-key ${DEPLOY_DIR_IMAGE}/u-boot-cap.key --certificate ${DEPLOY_DIR_IMAGE}/u-boot-cap.crt \
	--monotonic-count 1 --instance 0 --index 2 --guid ${FWUPDATE_FIP_ID} ${B}/${TFA_PLATFORM}/release/fip.bin ${DEPLOYDIR}/${FWUPDATE_FIP} \
"

do_compile:prepend() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'secure-boot', 'secure-boot', '', d)}" = "secure-boot" ]; then
		if [ "x${DEFAULT_ROT_KEY}" = "x${SECURE_BOOT_ROT_KEY}" ]; then
			bbwarn "SECURE_BOOT_ROT_KEY is not defined in local.conf, using development key for secure boot"
		fi

		bbnote "Key used for secure boot: ${SECURE_BOOT_ROT_KEY}"
	fi
}

do_install:append() {
	# The trusted-firmware-a.inc file modifies the Makefile by appending
	# compilation flags each time it is compiled, causing the Makefile
	# to become longer and longer.
	# Remove the appended flags during the installation stage to fix this issue.
	sed -i '/^LDLIBS/ s# \${BUILD_LDFLAGS\}##g' ${S}/tools/fiptool/Makefile
	sed -i '/^INCLUDE_PATHS/ s# \${BUILD_CFLAGS\}##g' ${S}/tools/fiptool/Makefile
	sed -i '/^LIB/ s# \${BUILD_LDFLAGS\}##g' ${S}/tools/cert_create/Makefile
}

do_deploy:append:bl31() {
	cp ${B}/${TFA_PLATFORM}/release/bl31/bl31.ld ${DEPLOY_DIR_IMAGE}/

	if [ "${@bb.utils.contains("DISTRO_FEATURES", "fwupdate", "1", "0", d)}" = "1" ]; then
		if [ "${@oe.utils.conditional("MACHINE", "genio-1200-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_FIP_ID="39961e72-5a8e-445a-90fe-ed68330744ec"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-1200-evk-ufs", "1", "", d)}" = "1" ]; then
			FWUPDATE_FIP_ID="0c1603a7-7f3d-427c-9ecc-f9b02d2fc729"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-700-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_FIP_ID="551cc92d-9a22-4dc8-8872-db1bb8dcafa0"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-510-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_FIP_ID="0cb9a4dd-8692-425d-a1dc-a33bcf52ad05"
		fi

		if [ "${@oe.utils.conditional("MACHINE", "genio-350-evk", "1", "", d)}" = "1" ]; then
			FWUPDATE_FIP_ID="82175ae7-f97e-44e0-8298-dcf223c94e8f"
		fi

		if [ -z "${FWUPDATE_FIP_ID}" ]; then
			bbwarn "FWUPDATE_FIP_ID is not defined, it can not support fwupdate."
		else
			mkeficapsule ${FWUPDATE_FIP_ARGS}
		fi
	fi
}
