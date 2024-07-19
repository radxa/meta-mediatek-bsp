require recipes-bsp/mtk-secure-boot-tools/mtk-secure-boot-tools.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/test-keys:"

SRC_URI:append = " \
    file://efuse.pem \
    file://da.pem \
"

COMPATIBLE_MACHINE = "mt8365|mt8395|mt8390|mt8370"

DEFAULT_EFUSE_KEY = "${WORKDIR}/efuse.pem"
DEFAULT_DA_KEY = "${WORKDIR}/da.pem"
EFUSE_KEY0 ?= "${DEFAULT_EFUSE_KEY}"
EFUSE_KEY ?= "${EFUSE_KEY0}"
SBC_KEY ?= "${EFUSE_KEY}"
DA_KEY ?= "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '${DEFAULT_DA_KEY}', '', d)}"

EXTRA_OEMAKE = "OUTDIR=${WORKDIR}/ SBC_KEY=${SBC_KEY} DA_KEY=${DA_KEY}"
EXTRA_OEMAKE:append = " EFUSE_KEY=${EFUSE_KEY} EFUSE_KEY1=${EFUSE_KEY1} EFUSE_KEY2=${EFUSE_KEY2} EFUSE_KEY3=${EFUSE_KEY3}"
EXTRA_OEMAKE:append:mt8365 = " PLATFORM=mt8365"
EXTRA_OEMAKE:append:mt8395 = " PLATFORM=mt8395"
EXTRA_OEMAKE:append:mt8390 = " PLATFORM=mt8390"
EXTRA_OEMAKE:append:mt8370 = " PLATFORM=mt8390"

DEPENDS:append = " openssl-native util-linux-native"

do_compile[nostamp] = "1"
do_compile() {
    if [ "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        if [ "x${DEFAULT_DA_KEY}" = "x${DA_KEY}" ]; then
            bbwarn "DA_KEY is not defined in local.conf, using development key for secure boot"
        fi
    fi

    if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        if [ "x${DEFAULT_EFUSE_KEY}" = "x${EFUSE_KEY}" ]; then
            bbwarn "BL2_KEY is not defined in local.conf, using development key for secure boot"
        fi
    fi

    SOC=`echo ${SOC_FAMILY} | tr [:lower:] [:upper:]`
    make ${EXTRA_OEMAKE} SOC=${SOC} -C ${S}/configs
}
