# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.15.42"
SRCBRANCH ?= "mtk-v5.15-dev"
SRCREV = "a46a21ed7edc5d31a304f860cd2b4c0ed7575358"

SRC_URI:append = " \
	file://0001-GENIO-media-i2c-Add-ite-it6510-driver.patch \
	file://0002-GENIO-media-i2c-it6510-Log-level-adjustment-for-log-.patch \
	file://misc.cfg \
"
