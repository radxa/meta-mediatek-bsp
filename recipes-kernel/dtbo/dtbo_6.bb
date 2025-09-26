# Copyright (C) 2024 Julien Stephan <jstephan@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

FILESEXTRAPATHS:prepend:mt8365-evk := "${THISDIR}/v6/mt8365-evk:"
FILESEXTRAPATHS:prepend:mt8365:= "${THISDIR}/v6/mt8365:"
FILESEXTRAPATHS:prepend:genio-510-evk:= "${THISDIR}/v6/genio-510-evk:"
FILESEXTRAPATHS:prepend:mt8370:= "${THISDIR}/v6/mt8370:"
FILESEXTRAPATHS:prepend:genio-700-evk:= "${THISDIR}/v6/genio-700-evk:"
FILESEXTRAPATHS:prepend:mt8390:= "${THISDIR}/v6/mt8390:"
FILESEXTRAPATHS:prepend:genio-1200-evk:= "${THISDIR}/v6/genio-1200-evk:"
FILESEXTRAPATHS:prepend:mt8395:= "${THISDIR}/v6/mt8395:"
FILESEXTRAPATHS:prepend:genio-720-evk := "${THISDIR}/v6/genio-720-evk:"
FILESEXTRAPATHS:prepend:mt8391 := "${THISDIR}/v6/mt8391:"
FILESEXTRAPATHS:prepend:genio-460-evk := "${THISDIR}/v6/genio-460-evk:"
FILESEXTRAPATHS:prepend:mt8367 := "${THISDIR}/v6/mt8367:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"
KERNEL_INCLUDE:append = " \
	${STAGING_KERNEL_BUILDDIR}/include \
	${STAGING_KERNEL_BUILDDIR}/include/generated \
"

SRC_URI:append:mt8395 = " \
	file://video.dts \
	file://apusys.dts \
"

SRC_URI:append:mt8391 = " \
	file://apusys.dts \
	file://video.dts \
"

SRC_URI:append:mt8390 = " \
	file://video.dts \
	file://apusys.dts \
	file://spi-test.dts \
"

SRC_URI:append:mt8370 = " \
	file://video.dts \
	file://apusys.dts \
"

SRC_URI:append:mt8365 = " \
	file://video.dts \
"

SRC_URI:append:mt8365-evk = " \
	file://camera-ap1302-ar0430-dual.dtsi \
	file://camera-ap1302-ar0430-single-csi0.dts \
	file://camera-ap1302-ar0430-single-csi1.dts \
	file://camera-ar0430-dual.dtsi \
	file://camera-ar0430-single-csi0.dts \
	file://camera-ar0430-single-csi1.dts \
	file://display-dsi.dts \
	file://display-hdmi.dts \
	file://display-lvds.dts \
	file://gpu-mali.dts \
	file://net-ethernet.dts \
	file://spi-test.dts \
	file://audio-sof.dts \
"

SRC_URI:append:genio-510-evk = " \
	file://isp71.dtsi \
	file://mtk-camera.dtsi \
	file://camera-common.dtsi \
	file://camera-imx214-csi0.dts \
	file://camera-imx214-csi1.dts \
	file://camera-imx214-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0.dts \
	file://camera-ar0430-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi0.dts \
	file://camera-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-2lanes-csi0.dts \
	file://camera-imx214-csi0-imx214-csi1.dts \
	file://camera-imx214-csi0-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi0-imx214-csi1.dts \
	file://camera-ar0830-ap1302-csi0-ar0830-ap1302-csi1.dts \
	file://camera-ar0430-ap1302-csi0-std.dts \
	file://camera-ar0830-ap1302-csi0-std.dts \
	file://camera-it6510-csi0-std.dts \
	file://camera-ar0830-ap1302-dual-std.dts \
	file://camera-it6510-dual-std.dts \
	file://camera-ar0830-ap1302-csi0-it6510-csi1-std.dts \
	file://camera-lt6911uxe-csi0-std.dts \
	file://camera-lt6911uxe-dual-std.dts \
	file://camera-ag190c-max9286-csi0-std.dts \
	file://camera-ag190c-max9286-dual-std.dts \
	file://camera-imx214-csi0-std.dts \
	file://camera-imx214-csi1-std.dts \
	file://display-dsi.dts \
	file://display-edp.dts \
	file://display-hdmi.dts \
	file://display-dp.dts \
	file://audio-sof.dts \
	file://spi-test.dts \
"

SRC_URI:append:genio-700-evk = " \
	file://isp71.dtsi \
	file://mtk-camera.dtsi \
	file://camera-common.dtsi \
	file://camera-imx214-csi0.dts \
	file://camera-imx214-csi1.dts \
	file://camera-imx214-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0.dts \
	file://camera-ar0430-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi0.dts \
	file://camera-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-2lanes-csi0.dts \
	file://camera-imx214-csi0-imx214-csi1.dts \
	file://camera-imx214-csi0-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi0-imx214-csi1.dts \
	file://camera-ar0830-ap1302-csi0-ar0830-ap1302-csi1.dts \
	file://camera-ar0430-ap1302-csi0-std.dts \
	file://camera-ar0830-ap1302-csi0-std.dts \
	file://camera-it6510-csi0-std.dts \
	file://camera-ar0830-ap1302-dual-std.dts \
	file://camera-it6510-dual-std.dts \
	file://camera-ar0830-ap1302-csi0-it6510-csi1-std.dts \
	file://camera-lt6911uxe-csi0-std.dts \
	file://camera-lt6911uxe-dual-std.dts \
	file://camera-ag190c-max9286-csi0-std.dts \
	file://camera-ag190c-max9286-dual-std.dts \
	file://camera-imx214-csi0-std.dts \
	file://camera-imx214-csi1-std.dts \
	file://display-dsi-p1v4.dts \
	file://display-dsi.dts \
	file://display-edp.dts \
	file://display-hdmi.dts \
	file://display-dp.dts \
	file://audio-sof.dts \
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
	file://display-dsi.dts \
	file://display-edp.dts \
	file://display-hdmi.dts \
	file://display-dp.dts \
	file://display-dsiedphdmi.dts \
	file://display-dsiedpdp.dts \
	file://display-dsihdmidp.dts \
	file://display-edphdmidp.dts \
	file://spi-test.dts \
"

SRC_URI:append:genio-720-evk = " \
	file://emmc.dts \
	file://ufs.dts \
	file://camera-common-std.dtsi \
	file://camera-imx258-csi0-std.dts \
	file://camera-imx258-csi1-std.dts \
	file://camera-imx258-dual-std.dts \
	file://camera-ov5640-csi0-std.dts \
	file://camera-ov5640-csi1-std.dts \
	file://camera-ov5640-dual-std.dts \
	file://camera-imx258-csi0-ov5640-csi1-std.dts \
	file://camera-gmsl-macro-def.dtsi \
	file://camera-gmsl-deserializer-channel.dtsi \
	file://camera-gmsl-serializer.dtsi \
	file://camera-gmsl-ag190h.dtsi \
	file://camera-ag190h-max96724-csi0-std.dts \
	file://camera-ag190h-max96724-csi1-std.dts \
	file://camera-ag190h-max96724-dual-std.dts \
	file://spi-test.dts \
	file://display-edp-4k.dts \
	file://display-edp-fhd.dts \
	file://display-dp.dts \
"

SRC_URI:append:genio-460-evk = " \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
