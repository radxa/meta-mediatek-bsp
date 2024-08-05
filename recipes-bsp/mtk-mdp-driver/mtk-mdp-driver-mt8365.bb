DESCRIPTION = "Mediatek MDP Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "263081f2c1df8875299c3f23351fe1018544c08c"
BRANCH = "mtk-v6.6-mt8167"

DEPENDS += "kernel-module-mtk-vcu-driver-mt8365"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8365"

SRC_URI += "git://gitlab.com/mediatek/aiot/bsp/mtk-mdp-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
