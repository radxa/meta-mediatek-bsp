require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot-tools.inc

SRC_URI += " \
    file://0002-libfdt-Fix-invalid-version-warning.patch \
"

SRC_URI:append:class-target = " \
    file://0001-tools-Remove-command-invocation-for-logo-data.patch \
"

DEPENDS += "gnutls openssl util-linux swig-native python3-setuptools-native"

do_install:append () {
	install -m 0755 tools/mkeficapsule ${D}${bindir}/uboot-mkeficapsule
	ln -sf uboot-mkeficapsule ${D}${bindir}/mkeficapsule
}

FILES:${PN} += " \
    ${bindir}/uboot-mkeficapsule \
    ${bindir}/mkeficapsule \
"
