# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.72"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "97b3a3febf30df543eb3d7c236139b0053a96378"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
