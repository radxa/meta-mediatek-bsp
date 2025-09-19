DESCRIPTION = "ITE IT6625 out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "59cbf2d48bf6cdfc487b5d306ca8ff9e46efe147"

BRANCH = "mtk-v6.6"

SRC_URI += "${AIOT_3RDPARTY_URI}/linux-drivers.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

MODULE_SUBDIR = "drivers/media/i2c/it6625"

MODULES_MODULE_SYMVERS_LOCATION = "${MODULE_SUBDIR}"
EXTRA_OEMAKE += "KERNEL_MODULE_SUBDIR=${S}/${MODULE_SUBDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-ite-it6625-driver"
