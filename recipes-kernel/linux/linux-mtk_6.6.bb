# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.72"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "c87d20d952ab621032f2ef163ead052178a86aa7"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
