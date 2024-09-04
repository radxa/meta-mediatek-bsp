DESCRIPTION = "Mediatek MDP Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "315cdddd670eaf35ff5dc6d316db38d6964682f0"
BRANCH = "mtk-v6.6-mt8167"

DEPENDS += "kernel-module-mtk-vcu-driver-mt8365"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8365"

SRC_URI += "${AIOT_BSP_URI}/mtk-mdp-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
