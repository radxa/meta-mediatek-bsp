# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.92"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "94c37053999087cfb48b967b2a23a3407065aa90"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
	${@bb.utils.contains_any('GPU_DRIVER', 'mali-gpu-driver', 'file://0002-GENIO-drm-mediatek-enable-renderD-for-mediatek-drm.patch', '', d)} \
	file://0001-GENIO-arm64-dts-mt8189-update-GPU-clock-configuratio.patch \
"
