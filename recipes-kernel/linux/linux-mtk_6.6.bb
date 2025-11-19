# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.92"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "54a59e08b20ed1b13858ebf7df24a1f8915fddb8"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
