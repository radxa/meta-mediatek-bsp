DESCRIPTION = "Mali GPU kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"


inherit module

SRCREV = "cd8b195781ba6e23c7e980c0b3f7c0b59037032c"

BRANCH = "mtk-v6.6"

SRC_URI += "${AIOT_BSP_URI}/mtk-mali-gpu-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "drivers/gpu/arm/midgard"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-gpu"

