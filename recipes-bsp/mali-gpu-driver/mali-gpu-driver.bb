DESCRIPTION = "Mali GPU kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"


inherit module

SRCREV = "9cf015778fd6e326e267cd4fa91d27ae6f6ac9ae"

BRANCH = "mtk-v6.6"

SRC_URI += "${AIOT_BSP_URI}/mtk-mali-gpu-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "drivers/gpu/arm/midgard"
KERNEL_MODULE_AUTOLOAD += "mali_kbase"
KERNEL_MODULE_PROBECONF += "mali_kbase"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-gpu"

# Both Panfrost and mali_kbase matches the same
# Mali GPU device tree compatibles.
# So we blacklist panfrost to make sure mali_kbase gets loaded.
module_conf_mali_kbase = "blacklist panfrost"
