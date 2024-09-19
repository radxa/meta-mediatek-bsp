# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt DDR configuration library"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_RITY_URI}/libdram-prebuilt.git;protocol=https;branch=main"
SRCREV = "84fd1c9119879d39b7f416c15377dc538f72076d"

do_install() {
	install -d ${D}${libdir}
	install -m 644 ${S}/${LIBDRAM_BOARD_NAME}/libdram.a ${D}${libdir}
}
