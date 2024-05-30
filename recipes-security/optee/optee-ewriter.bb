# Copyright (C) 2022 Ryan Cho <ryan.cho@mediatek.com>
# Released under BSD-2-Clause & MediatekPropritary license

require recipes-security/optee/optee.inc

inherit features_check

REQUIRED_DISTRO_FEATURES = "nda-mtk"
REQUIRED_MACHINE_FEATURES = "optee-efuse"

SUMMARY = "Mediatek OP-TEE eFuse Writer"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=71cd0d8e7551828e714ab8e9c99ed74b"

SRC_URI = "${AIOT_NDA_URI}/optee-ewriter.git;protocol=https;branch=main"
SRCREV = "490ec7b0cbdec104c126410a50f3f37fa37fb99e"
COMPATIBLE_MACHINE = "mt*"
DEPENDS:append = " optee-client"
PROVIDES = " libtz_efuse"

S = "${WORKDIR}/git"

RPROVIDES_${PN} += " libtz_efuse.so()(64bit)"
FILES_${PN} += "${nonarch_base_libdir}/optee_armtz/"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

EXTRA_OEMAKE += "TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
                 CROSS_COMPILE_HOST=${HOST_PREFIX} \
                 CROSS_COMPILE_TA=${HOST_PREFIX} \
                "

do_compile() {
    oe_runmake LIBDIR="${STAGING_DIR_TARGET}/${libdir}" INCDIR="${STAGING_DIR_TARGET}/${includedir}"
}

do_install () {
    cd ${S}
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    oe_runmake install LIBDIR="${D}${libdir}" BINDIR="${D}${bindir}"
}
