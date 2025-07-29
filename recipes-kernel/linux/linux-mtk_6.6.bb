# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.62"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "9ab788e85f4be171bd65e8b80d57404e04a0fe74"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
