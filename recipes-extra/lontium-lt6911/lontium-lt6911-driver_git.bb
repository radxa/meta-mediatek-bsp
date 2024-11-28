DESCRIPTION = "Lontium LT6911UXE out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "6306c5371d57317ffe5e30067c5315e6738893a6"

BRANCH = "master"

SRC_URI += "${AIOT_BSP_URI}/lontium-lt6911-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-lontium-lt6911-driver"
