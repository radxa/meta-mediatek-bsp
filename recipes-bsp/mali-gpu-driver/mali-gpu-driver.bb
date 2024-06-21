DESCRIPTION = "Mali GPU kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"


inherit module

SRCREV = "456b673f370231034cadc74ad07495db96d03532"

BRANCH = "r${MALI_VERSION}p0"

# mt8183 is not official supported by IoT, keep kernel driver in version r32
SRCREV:mt8183 = "4b2e33a50d049b51b84083b502ae3d3ad45c0cc1"
BRANCH:mt8183 = "r32p0"

SRC_URI += "${AIOT_BSP_URI}/mtk-mali-gpu-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "drivers/gpu/arm/midgard"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-gpu"

