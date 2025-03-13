# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek DDR configuration library"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit meson

EXTRA_OEMESON:append = " -Dboard=${LIBDRAM_BOARD_NAME}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/libdram.git;protocol=ssh;branch=main"
SRCREV = "35d0209cae153b8f29de0c869a0dd63c56d49bf8"

FULL_OPTIMIZATION = "-Os"
SECURITY_CFLAGS = ""

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"
