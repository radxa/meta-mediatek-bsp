# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.37"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "d61472d8d6762a3bf4e3101e0abcfc0bd98b38ee"

SRC_URI:append = " \
	file://0001-GENIO-drivers-lib-Strip-path-components.patch \
"
