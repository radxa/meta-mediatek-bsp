# Copyright (C) 2021 Ryan Cho <ryan.cho@mediatek.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt Basic configuration library"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_BSP_URI}/libbase-prebuilt.git;protocol=https;branch=main"
SRCREV = "f765b233f6d4c6c8b24979f746772b9c6ae9c900"
