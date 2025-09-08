# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.87"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "4214148a4324f231cc683d12edf7b2c7e8647182"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
