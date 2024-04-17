DESCRIPTION = "ITE IT6510 out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "74b4bbdb14d967a5000bd248a74628d7c64b2049"

BRANCH = "mtk-v6.6"

SRC_URI += "${AIOT_BSP_URI}/ite-it6510-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-ite-it6510-driver"
