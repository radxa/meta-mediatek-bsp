# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.15.47"
SRCBRANCH ?= "mtk-v5.15-dev-rity-kirkstone-v24.0"
SRC_URI = "git://git@github.com/radxa/kernel.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "608672b1b66cf62a5d90c8779d611d237ed6d858"

