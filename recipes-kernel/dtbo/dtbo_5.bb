# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

FILESEXTRAPATHS:prepend := "${THISDIR}/v5/common:"
FILESEXTRAPATHS:prepend:mt8167-coral := "${THISDIR}/v5/mt8167-coral:"
FILESEXTRAPATHS:prepend:mt8167-pumpkin := "${THISDIR}/v5/mt8167-pumpkin:"
FILESEXTRAPATHS:prepend:mt8167-sb30 := "${THISDIR}/v5/mt8167-sb30:"
FILESEXTRAPATHS:prepend:mt8516-pumpkin:= "${THISDIR}/v5/mt8516-pumpkin:"
FILESEXTRAPATHS:prepend:mt8183-evb := "${THISDIR}/v5/mt8183-evb:"
FILESEXTRAPATHS:prepend:mt8183-pumpkin:= "${THISDIR}/v5/mt8183-pumpkin:"
FILESEXTRAPATHS:prepend:mt8365-evk := "${THISDIR}/v5/mt8365-evk:"
FILESEXTRAPATHS:prepend:mt8365-pumpkin := "${THISDIR}/v5/mt8365-pumpkin:"
FILESEXTRAPATHS:prepend:mt8365-sb35:= "${THISDIR}/v5/mt8365-sb35:"
FILESEXTRAPATHS:prepend:mt8365:= "${THISDIR}/v5/mt8365:"
FILESEXTRAPATHS:prepend:mt8195-demo:= "${THISDIR}/v5/mt8195-demo:"
FILESEXTRAPATHS:prepend:mt8395:= "${THISDIR}/v5/mt8395:"
FILESEXTRAPATHS:prepend:genio-700-evk:= "${THISDIR}/v5/genio-700-evk:"
FILESEXTRAPATHS:prepend:mt8390:= "${THISDIR}/v5/mt8390:"
FILESEXTRAPATHS:prepend:genio-1200-evk:= "${THISDIR}/v5/genio-1200-evk:"
FILESEXTRAPATHS:prepend:genio-1200-evk-p1v1:= "${THISDIR}/v5/genio-1200-evk-p1v1:"
FILESEXTRAPATHS:prepend:mt8370:= "${THISDIR}/v5/mt8370:"
FILESEXTRAPATHS:prepend:genio-510-evk:= "${THISDIR}/v5/genio-510-evk:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"
KERNEL_INCLUDE:append = " \
	${STAGING_KERNEL_BUILDDIR}/include \
	${STAGING_KERNEL_BUILDDIR}/include/generated \
"

SRC_URI:append = " \
	file://panel-raspberrypi.dtsi \
"

SRC_URI:append:mt8167-pumpkin = " \
	file://panel-raspberrypi.dts \
	file://panel-avd-tt70ws-cn-134-a.dts \
	file://vesper.dts \
	file://audio-i2s.dts \
	file://camera-ov5645.dts \
"

SRC_URI:append:mt8167-sb30 = " \
	file://panel-raspberrypi.dts \
"

SRC_URI:append:mt8167-coral = " \
	file://camera-ov5645.dts \
"

SRC_URI:append:mt8183-evb = " \
	file://panel-tpv-otm1901a.dts \
	file://panel-truly-r63350a.dts \
"

SRC_URI:append:mt8183-pumpkin = " \
	file://panel-raspberrypi.dts \
	file://panel-urt-umo9465md.dts \
	file://camera-ar0330-dual.dtsi \
	file://camera-ar0330-single.dts \
	file://camera-ar0330-dual.dts \
	file://camera-ap1302-ar0330-dual-ar0144-dual.dtsi \
	file://camera-ap1302-ar0330-single.dts \
	file://camera-ap1302-ar0144-single.dts \
	file://camera-ap1302-ar0144-dual.dts \
	file://camera-ap1302-ar0330-single-ar0144-single.dts \
	file://camera-ap1302-ar0330-single-ar0144-dual.dts \
	file://camera-ap1302-ar1335-single.dts \
        file://camera-ap1302-ar0430-dual.dtsi \
        file://camera-ap1302-ar0430-single.dts \
        file://camera-ap1302-ar0430-dual.dts \
"

SRC_URI:append:mt8365-evk = " \
	file://net-ethernet.dts \
	file://camera-ap1302-ar0430-dual.dtsi \
	file://camera-ap1302-ar0430-single-csi0.dts \
	file://camera-ap1302-ar0430-single-csi1.dts \
	file://display-dsi.dts \
	file://display-hdmi.dts \
	file://display-lvds.dts \
	file://display-headless.dts \
"

SRC_URI:append:mt8365-pumpkin = " \
        file://camera-ap1302-ar0144-dual.dtsi \
        file://camera-ap1302-ar0144-dual.dts \
        file://camera-ap1302-ar0144-single.dts \
        file://camera-ap1302-ar0430-dual.dtsi \
        file://camera-ap1302-ar0430-dual.dts \
        file://camera-ap1302-ar0430-single.dts \
        file://camera-ap1302-ar0330-dual.dtsi \
        file://camera-ap1302-ar0330-dual.dts \
        file://camera-ap1302-ar0330-single.dts \
        file://camera-ap1302-ar0330-dual-ar0144-dual.dtsi \
        file://camera-ap1302-ar0330-dual-ar0144-dual.dts \
        file://camera-ap1302-ar0330-single-ar0144-dual.dts \
        file://camera-ap1302-ar0330-single-ar0144-single.dts \
        file://camera-ap1302-ar0330-dual-ar0430-dual.dtsi \
        file://camera-ap1302-ar0330-dual-ar0430-dual.dts \
        file://camera-ap1302-ar0330-single-ar0430-dual.dts \
        file://camera-ap1302-ar0330-single-ar0430-single.dts \
        file://camera-ar0330-dual.dtsi \
        file://camera-ar0330-single-csi0.dts \
        file://camera-ar0330-single-csi1.dts \
        file://panel-urt-umo9465md.dts \
        file://camera-thp7312-imx258-dual.dtsi \
        file://camera-thp7312-imx258-single-csi0.dts \
        file://camera-thp7312-imx258-single-csi1.dts \
"

SRC_URI:append:mt8365-sb35 = " \
	file://panel-raspberrypi.dts \
"

SRC_URI:append:mt8516-pumpkin = " \
	file://vesper.dts \
	file://audio-i2s.dts \
"

SRC_URI:append:mt8195-demo = " \
	file://panel-boe-ne135fbm.dts \
	file://display-dp.dts \
	file://display-dsi.dts \
	file://display-edp4k.dts \
	file://isp70.dtsi \
	file://camera-imx214-csi0.dts \
	file://mtk-camera.dtsi \
"

SRC_URI:append:genio-700-evk = " \
	file://display-dsi.dts \
	file://display-dsi2lvds.dts \
	file://display-edp.dts \
	file://display-hdmi.dts \
	file://display-dp.dts \
	file://display-dsiedp.dts \
	file://display-dsidp.dts \
	file://display-lvdsedp.dts \
	file://display-lvdshdmi.dts \
	file://display-lvdsdp.dts \
	file://display-edpdp.dts \
	file://display-edphdmi.dts \
	file://display-hdmidp.dts \
	file://display-headless.dts \
	file://isp71.dtsi \
	file://mtk-camera.dtsi \
	file://camera-imx214-csi0.dts \
	file://camera-imx214-csi1.dts \
	file://camera-imx214-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0.dts \
	file://camera-ar0430-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi0.dts \
	file://camera-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0-std.dts \
	file://camera-ar0830-ap1302-csi0-std.dts \
	file://camera-it6510-csi0-std.dts \
	file://camera-ar0830-ap1302-dual-std.dts \
	file://camera-it6510-dual-std.dts \
	file://camera-ar0830-ap1302-csi0-it6510-csi1-std.dts \
"

SRC_URI:append:mt8395 = " \
	file://gpu-mali.dts \
	file://video.dts \
	file://apusys.dts \
"

SRC_URI:append:mt8390 = " \
	file://gpu-mali.dts \
	file://video.dts \
	file://apusys.dts \
"

SRC_URI:append:mt8370 = " \
	file://gpu-mali.dts \
	file://video.dts \
	file://apusys.dts \
"

SRC_URI:append:mt8365 = " \
	file://gpu-mali.dts \
	file://video.dts \
"

SRC_URI:append:genio-1200-evk = " \
	file://display-dsi.dts \
	file://display-edp.dts \
	file://display-edp2lvds.dts \
	file://display-hdmi.dts \
	file://display-dp.dts \
	file://display-dsiedp.dts \
	file://display-dsilvds.dts \
	file://display-dsidp.dts \
	file://display-edphdmi.dts \
	file://display-edpdp.dts \
	file://display-lvdshdmi.dts \
	file://display-lvdsdp.dts \
	file://display-hdmidp.dts \
	file://display-dsiedphdmi.dts \
	file://display-dsiedpdp.dts \
	file://display-dsilvdshdmi.dts \
	file://display-dsilvdsdp.dts \
	file://display-dsihdmidp.dts \
	file://display-edphdmidp.dts \
	file://display-lvdshdmidp.dts \
	file://display-headless.dts \
	file://isp70.dtsi \
	file://mtk-camera.dtsi \
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
	file://camera-ar0430-ap1302-csi0-std.dts\
	file://camera-ar0830-ap1302-csi0-std.dts\
	file://camera-it6510-csi0-std.dts \
"

SRC_URI:append:genio-1200-evk-p1v1 = " \
	file://display-edp2lvds.dts \
	file://display-dp.dts \
	file://display-hdmidp.dts \
	file://display-dsilvds.dts \
	file://display-edp.dts \
	file://display-dsiedp.dts \
	file://display-dsiedpdp.dts \
	file://display-dsiedphdmi.dts \
	file://display-dsihdmidp.dts \
	file://display-dsilvdsdp.dts \
	file://display-dsilvdshdmi.dts \
	file://display-edphdmidp.dts \
	file://display-lvdshdmidp.dts \
	file://display-headless.dts \
	file://isp70.dtsi \
	file://mtk-camera.dtsi \
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
"

SRC_URI:append:genio-510-evk = " \
	file://display-dsi.dts \
	file://display-dsi2lvds.dts \
	file://display-edp.dts \
	file://display-hdmi.dts \
	file://display-dp.dts \
	file://display-dsiedp.dts \
	file://display-dsidp.dts \
	file://display-lvdsedp.dts \
	file://display-lvdshdmi.dts \
	file://display-lvdsdp.dts \
	file://display-edpdp.dts \
	file://display-edphdmi.dts \
	file://display-hdmidp.dts \
	file://display-headless.dts \
	file://isp71.dtsi \
	file://mtk-camera.dtsi \
	file://camera-imx214-csi0.dts \
	file://camera-imx214-csi1.dts \
	file://camera-imx214-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0.dts \
	file://camera-ar0430-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-csi0.dts \
	file://camera-ar0830-ap1302-csi1.dts \
	file://camera-ar0830-ap1302-2lanes-csi0.dts \
	file://camera-ar0430-ap1302-csi0-std.dts \
	file://camera-ar0830-ap1302-csi0-std.dts \
	file://camera-it6510-csi0-std.dts \
	file://camera-ar0830-ap1302-dual-std.dts \
	file://camera-it6510-dual-std.dts \
	file://camera-ar0830-ap1302-csi0-it6510-csi1-std.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
