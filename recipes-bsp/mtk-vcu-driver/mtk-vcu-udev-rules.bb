# Copyright (C) 2025 MediaTek Inc.
# Author: Macross Chen <macross.chen@mediatek.com>

SUMMARY = "Shared udev rules for Mediatek VCU out-of-tree kernel driver packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://99-mtk-vcu.rules"

do_install () {
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/99-mtk-vcu.rules ${D}/${sysconfdir}/udev/rules.d/99-mtk-vcu.rules
}

FILES:${PN} += "${sysconfdir}/udev/rules.d/99-mtk-vcu.rules"
