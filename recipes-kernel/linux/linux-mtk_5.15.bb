# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.15.37"
SRCBRANCH ?= "mtk-v5.15-dev"
SRCREV = "795e5ed5d350dd5acc0d12ee7c4c7eb9c49c7d53"

SRC_URI:append:mt8195 = " \
	file://0001-GENIO-media-i2c-modify-imx214-to-support-extra-exten.patch \
	file://0002-HACK-GENIO-media-i2c-Replace-mbus_code-to-mtk_mbus_c.patch \
"
