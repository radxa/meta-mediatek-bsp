# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.47"
SRCBRANCH ?= "mtk-v6.6"
SRCREV = "c8201a2744fc3a57b3a516e6ef52853b3f628430"

SRC_URI:append = " \
	file://0001-GENIO-drivers-Strip-path-components.patch \
"
