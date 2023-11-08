# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.15.42"
SRCBRANCH ?= "mtk-v5.15-dev"
SRCREV = "7781acdccbafb70f2bfedddb8256d5e2df84df4d"

SRC_URI:append = " \
	file://0001-GENIO-media-i2c-Add-ite-it6510-driver.patch \
"
