# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MDPD service"
LICENSE = "CLOSED"

RPROVIDES:${PN} += "libmdpd.so()(64bit)"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/mdpd.git;protocol=https;branch=main"
SRCREV = "eef0034c15ac8c9863a016fb125c43c17b5bd57b"

DEBUG_BUILD = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

EXTRA_OEMAKE = " mdpd "

EXTRA_OEMAKE:append:mt8365 = ' \
	libmdp.mt8365.so \
	V4l2MDPD_CFLAGS=-DMDP_KERNEL_IPI_COMPATIBLE \
'

require mdpd.inc
