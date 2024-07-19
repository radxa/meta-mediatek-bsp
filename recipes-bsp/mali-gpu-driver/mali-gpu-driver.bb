DESCRIPTION = "Mali GPU kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"


inherit module

SRCREV = "456b673f370231034cadc74ad07495db96d03532"

BRANCH = "r${MALI_VERSION}p0"

SRC_URI += "${AIOT_BSP_URI}/mtk-mali-gpu-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "drivers/gpu/arm/midgard"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-gpu"

