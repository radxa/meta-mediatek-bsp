S = "${WORKDIR}/git"
BUILD ?= "build-${LK_PROJECT}"

DEPENDS = "libdram-lk libbase-lk-prebuilt"

LICENSE = "MIT & BSD-2-Clause & LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5e70cccfd7c167a0ace6a1eb1d5457 \
                    file://lib/fdt/LICENSE;md5=abf7cd4d9c12ad07158318352d560fdb \
                    file://LICENSE.aiot;md5=6fcd7dfec853b4eca3b44877b51c0943 "

SRC_URI = "${AIOT_NDA_URI}/lk.git;protocol=https;branch=main"
SRCREV = "69ac739655e6f43650b4e27816bb4f65aeb4f41c"

EXTRA_OEMAKE += "LIBDRAM=${STAGING_DIR_TARGET}/${libdir}/libdram-lk.a"
EXTRA_OEMAKE += "LIBBASE=${STAGING_DIR_TARGET}/${libdir}/libbase-lk.a"

do_compile () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}			\
			   CFLAGS=""						\
			   DEBUG=0						\
			   SECURE_BOOT_ENABLE=no				\
			   LIBGCC=""						\
			   GLOBAL_CFLAGS="-mstrict-align -mno-outline-atomics"	\
			   ${LK_PROJECT}
}

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"

require lk.inc
