DESCRIPTION = "Mediatek CAMISP-MT8395 Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6760c75814a7f07fc569c7ca1fd5c35b"

COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370"

inherit module

SRCREV = "9b3774819846570c0c8fead0d4e683125a95b489"

BRANCH = "mtk-v6.6"

SRC_URI += "${AIOT_BSP_URI}/mtk-camisp-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

DEPENDS += "kernel-module-mtk-vcu-driver-mt8395"

python() {
    plat = d.getVar('SOC_FAMILY', True)
    if plat == 'mt8370':
        plat = 'mt8188'
    d.setVar('PLATFORM', plat)
}

EXTRA_OEMAKE:append = " PLATFORM=${PLATFORM} "

do_install:append() {
    install -d ${D}${nonarch_base_libdir}/firmware/
    dd if=/dev/zero of=${D}${nonarch_base_libdir}/firmware/remoteproc_scp bs=1 count=100
}

FILES:${PN} += " \
    ${nonarch_base_libdir}/firmware/remoteproc_scp \
"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-${BPN}"
