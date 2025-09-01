# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.87"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "be2dbdd837b37acf23fa6ba601d8ae46e07245bc"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"

SRC_URI:append:mt8391 = " \
	file://0001-GENIO-drm-mediatek-Add-DVO-and-eDP-driver-for-MT8391.patch \
	file://0002-GENIO-phy-mediatek-Add-eDP-phy-driver-for-MT8391.patch \
	file://0003-GENIO-arm64-dts-mt8391-Add-DVO-and-eDP-nodes.patch \
"
