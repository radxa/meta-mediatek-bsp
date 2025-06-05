# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.37"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "d24608b4359ab206ff04e4e80f74976b4363c2b8"

SRC_URI:append = " \
	file://0001-GENIO-drivers-lib-Strip-path-components.patch \
"
