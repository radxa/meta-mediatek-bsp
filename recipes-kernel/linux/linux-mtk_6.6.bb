# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.57"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "4487095e44a7b1c40b9a14fda4340b2b69a17632"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
