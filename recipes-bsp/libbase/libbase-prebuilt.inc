# Copyright (C) 2021 Ryan Cho <ryan.cho@mediatek.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt Basic configuration library"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_BSP_URI}/libbase-prebuilt.git;protocol=https;branch=main"
SRCREV = "52a3aab16d0f7c0c9ec817acf87b1e973965b7de"

python() {
    plat = d.getVar('SOC_FAMILY', True)
    if plat == 'mt8367':
        plat = 'mt8391'
    d.setVar('SOC_FAMILY', plat)
}
