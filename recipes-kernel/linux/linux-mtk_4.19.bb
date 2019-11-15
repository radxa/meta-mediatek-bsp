# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.84"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v4.19"
SRCREV = "77fc07538c903cf58d75bf7808a36755914f2b14"

SRC_URI_append = " \
	file://defconfig \
	file://usb_uvc.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'file://ts_ft5x06.cfg', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'file://vesper.cfg', '', d)} \
"
