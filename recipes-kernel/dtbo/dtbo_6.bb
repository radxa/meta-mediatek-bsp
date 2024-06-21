# Copyright (C) 2024 Julien Stephan <jstephan@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

FILESEXTRAPATHS:prepend:mt8365-evk := "${THISDIR}/v6/mt8365-evk:"
FILESEXTRAPATHS:prepend:mt8365:= "${THISDIR}/v6/mt8365:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"
KERNEL_INCLUDE:append = " \
	${STAGING_KERNEL_BUILDDIR}/include \
	${STAGING_KERNEL_BUILDDIR}/include/generated \
"

SRC_URI:append:mt8365-evk = " \
	file://camera-ap1302-ar0430-dual.dtsi \
	file://camera-ap1302-ar0430-single-csi0.dts \
	file://camera-ap1302-ar0430-single-csi1.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
