# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.67"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "ac8ffd8ea5efeec4a4f8ecac27f12e868469d266"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"

SRC_URI:append:mt8391 = " \
	file://0002-G720-GENIO-arm64-dts-mt8189-add-mmc1-node.patch \
	file://0003-G720-GENIO-arm64-dts-mt8391-genio-720-evk-add-mmc1-node.patch \
	file://0004-G720-GENIO-mmc-host-workaround-before-spm-is-ready.patch \
"
