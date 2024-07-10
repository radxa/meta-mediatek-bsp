# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc

do_assemble_fitimage[depends] += "virtual/bootloader:do_populate_sysroot"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# Embed SRCREV into the version string;
# Use "linux-mtk+modified" if using "devtool modify linux-mtk"
LINUX_VERSION_EXTENSION = "-mtk+${@("modified" if d.getVar('EXTERNALSRC') else ("g" + d.getVar('SRCREV')[0:12]))}"

S = "${WORKDIR}/git"

SRC_URI:append = " \
	${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
"

# Board specific config fragments
SRC_URI:append:mt8370-evk = " file://mt8370-evk.cfg "
SRC_URI:append:mt8390-evk = " file://mt8390-evk.cfg "
SRC_URI:append:mt8395-evk = " file://mt8395-evk.cfg "
SRC_URI:append:mt8395-evk-p1v1 = " file://mt8395-evk-p1v1.cfg "
SRC_URI:append:mt8195-demo = " file://mt8195-demo.cfg "
SRC_URI:append:mt8365-evk = " file://mt8365-evk.cfg "
SRC_URI:append:mt8365-sb35 = " file://mt8365-sb35.cfg "
SRC_URI:append:mt8365-pumpkin = " file://mt8365-pumpkin.cfg "
SRC_URI:append:mt8183-pumpkin = " file://mt8183-pumpkin.cfg "
SRC_URI:append:mt8183-evb = " file://mt8183-evb.cfg "
SRC_URI:append:mt8167-pumpkin = " file://mt8167-pumpkin.cfg "
SRC_URI:append:mt8167-sb30 = " file://mt8167-sb30.cfg "
SRC_URI:append:mt8167-coral = " file://mt8167-coral.cfg "
SRC_URI:append:i300-pumpkin = " \
	file://i300-pumpkin.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'file://vesper.cfg', '', d)} \
"

# SoC specific config fragments
SRC_URI:append:mt8167 = " file://mt8167.cfg file://mt8516.cfg "
SRC_URI:append:mt8183 = " file://mt8183.cfg "
SRC_URI:append:mt8195 = " \
	file://mt8195.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'tsn', 'file://tsn.cfg', '', d)} \
"
SRC_URI:append:mt8365 = " file://mt8365.cfg "
SRC_URI:append:mt8516 = " file://mt8516.cfg "
SRC_URI:append:mt8188 = " \
	file://mt8188.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'tsn', 'file://tsn.cfg', '', d)} \
"

SRC_URI:append:mt8370 = " \
	file://mt8370.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'tsn', 'file://tsn.cfg', '', d)} \
"

# Connectivity config fragments
SRC_URI:append:mt7663 = " \
	${@bb.utils.contains('MT7663_WIFI_USE_UPSTREAM_DRV', '1', 'file://mt7663.cfg', 'file://mt76xx-tk-wifi.cfg', d)} \
	${@bb.utils.contains('MT7663_BT_USE_UPSTREAM_DRV', '1', 'file://btmtksdio.cfg', 'file://mt76xx-tk-bt.cfg', d)} \
"
SRC_URI:append:mt7668 = " file://mt76xx-tk-wifi.cfg file://btmtksdio.cfg "
SRC_URI:append:mt7921 = " file://mt7921.cfg "

KERNEL_EXTRA_ARGS = "Image.gz dtbs"
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

export DTC_FLAGS = '-@'

SRC_URI = "${AIOT_BSP_URI}/linux.git;protocol=https;branch=${SRCBRANCH}"
PV = "${LINUX_VERSION}+git${SRCPV}"

uboot_prep_kimage() {
	linux_comp="gzip"
	cp arch/arm64/boot/Image.gz linux.bin
	echo ${linux_com}
}

do_bundle_initramfs:prepend() {
	if [ x"${@bb.utils.contains("DISTRO_FEATURES", "security", "1", "0", d)}" = x1 -a x"${INITRAMFS_IMAGE_BUNDLE}" = x1 ]; then
		echo "CONFIG_INITRAMFS_SOURCE=\"${B}/usr/${INITRAMFS_IMAGE_NAME}.cpio\" CONFIG_INITRAMFS_COMPRESSION_GZIP=y" >> ${B}/.config
	fi
}

COMPATIBLE_MACHINE_mediatek-bsp = "mediatek-bsp"
