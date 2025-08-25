# Copyright (C) 2022 Ryan Cho <ryan.cho@mediatek.com>
# Released under MediatekPropritary license

SUMMARY = "MediaTek Prebuilt eFuse library"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=71cd0d8e7551828e714ab8e9c99ed74b"

inherit features_check

REQUIRED_DISTRO_FEATURES = "nda-mtk"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/libefuse-pta-prebuilt.git;protocol=https;branch=main"
SRCREV = "971cee6a9c9b601d8ca9fa4d7b2e204f67b7da7c"

do_install() {
        install -d ${D}${libdir}
        install -m 644 ${S}/${SOC_FAMILY}/libefuse.a ${D}${libdir}
}
