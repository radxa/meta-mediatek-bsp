SUMMARY = "OP-TEE UC FDE"
DESCRIPTION = "An application for UC FDE deployment"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://README.md;md5=d7ad866eb4b49c8379d758b0ac3737f5"

inherit python3native
require recipes-security/optee/optee.inc

DEPENDS = "optee-client optee-os-tadevkit python3-cryptography-native libgcc"

# Since this SRC_URI is temporary, we will update it later with a permanent SRC_URI.
SRC_URI = "git://git.launchpad.net/~ondrak/+git/optee-uc-fde;branch=master;protocol=https"
SRCREV = "7dbf8170632ed92b090c1eb3b4044c11cfd6ddba"

# Imports machine specific configs
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "mt*"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

EXTRA_OEMAKE += " \
    TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
    TA_CROSS_COMPILE=${TARGET_PREFIX} \
    CROSS_COMPILE=${TARGET_PREFIX} \
    CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_HOST} -I${FDE_DEV}" O=${B} \
"

do_compile() {
    oe_runmake -C ${S}/ta
}
do_compile[cleandirs] = "${B}"

do_install () {
    mkdir -p ${D}${nonarch_base_libdir}/optee_armtz/
    install -D -p -m0444 ${B}/ta/*/*.stripped.elf ${D}${nonarch_base_libdir}/optee_armtz/
    install -D -p -m0444 ${B}/ta/*/*.ta ${D}${nonarch_base_libdir}/optee_armtz/
}

FILES:${PN} += " ${nonarch_base_libdir}/optee_armtz/"
