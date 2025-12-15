DESCRIPTION = "Mediatek APUSYS Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370|mt8391"

BRANCH = "android13"
BRANCH:mt8391 = "android15"

SRCREV = "57ccb1aead8331d5807bcbdc951b544edba4e7d8"
SRCREV:mt8391 = "945deed32d52cca7903c25e864ac0c703c968028"

SRC_URI += "git://gitlab.com/mediatek/aiot/bsp/mtk-apusys-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "KERNEL_SRC=${STAGING_KERNEL_DIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
