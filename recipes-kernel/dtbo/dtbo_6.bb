# Copyright (C) 2024 Julien Stephan <jstephan@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

FILESEXTRAPATHS:prepend:mt8365-evk := "${THISDIR}/v6/mt8365-evk:"
FILESEXTRAPATHS:prepend:mt8365:= "${THISDIR}/v6/mt8365:"
FILESEXTRAPATHS:prepend:genio-1200-evk:= "${THISDIR}/v6/genio-1200-evk:"
FILESEXTRAPATHS:prepend:mt8395:= "${THISDIR}/v6/mt8395:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"
KERNEL_INCLUDE:append = " \
	${STAGING_KERNEL_BUILDDIR}/include \
	${STAGING_KERNEL_BUILDDIR}/include/generated \
"

SRC_URI:append:mt8365 = " \
	file://video.dts \
"

SRC_URI:append:mt8365-evk = " \
	file://camera-ap1302-ar0430-dual.dtsi \
	file://camera-ap1302-ar0430-single-csi0.dts \
	file://camera-ap1302-ar0430-single-csi1.dts \
	file://display-dsi.dts \
	file://display-hdmi.dts \
	file://gpu-mali.dts \
	file://net-ethernet.dts \
"

SRC_URI:append:genio-1200-evk = " \
	file://audio-sof.dts \
	file://isp70.dtsi \
	file://mtk-camera.dtsi \
	file://camera-common.dtsi \
	file://camera-imx214-csi0.dts \
	file://camera-imx214-csi1.dts \
	file://camera-imx214-csi2.dts \
	file://camera-imx214-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0.dts \
	file://camera-ar0430-ap1302-csi1.dts \
	file://camera-ar0430-ap1302-csi2.dts \
	file://camera-ar0830-ap1302-csi0.dts \
	file://camera-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi2.dts \
	file://camera-ar0830-ap1302-2lanes-csi0.dts \
	file://camera-imx214-csi0-ar0830-ap1302-csi1.dts \
	file://camera-imx214-csi0-ar0830-ap1302-csi2.dts \
	file://camera-imx214-csi0-ar0830-ap1302-csi1-imx214-csi2.dts \
	file://camera-imx214-csi0-ar0830-ap1302-csi1-ar0830-ap1302-csi2.dts \
	file://camera-imx214-csi0-imx214-csi2.dts \
	file://camera-ar0830-ap1302-csi0-ar0830-ap1302-csi2.dts \
	file://camera-ar0430-ap1302-csi0-std.dts\
	file://camera-ar0830-ap1302-csi0-std.dts\
	file://camera-it6510-csi0-std.dts \
	file://camera-lt6911uxe-csi0-std.dts \
	file://camera-ag190c-max9286-csi0-std.dts \
	file://camera-imx214-csi0-std.dts \
	file://camera-imx214-csi1-std.dts \
	file://camera-imx214-csi2-std.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
