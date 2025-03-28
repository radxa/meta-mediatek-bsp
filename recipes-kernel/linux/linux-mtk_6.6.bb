# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.37"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "bf916d7cc74c309e2fc6d5bae5a55fb33424ada0"

SRC_URI:append = " \
	file://0001-GENIO-drivers-lib-Strip-path-components.patch \
"
