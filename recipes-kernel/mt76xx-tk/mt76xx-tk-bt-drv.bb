# Copyright (C) 2023 MediaTek, Inc.
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7663 Bluetoth driver"
LICENSE = "CLOSED"

inherit module

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_driver/turnkey_sdio;protocol=ssh;branch=main"
SRCREV = "303fbabf3eb4d442b518ef8101754b7a224fbc2f"

S = "${WORKDIR}/git"

DEPENDS += "mt76xx-tk-wifi-drv"

EXTRA_OEMAKE+=" \
	MTK_PLATFORM= \
	WLAN_CHIP_ID=${MTK_WIRELESS_CHIP}\
	WLAN_RESET_KO_INCLUDE+="-I${STAGING_INCDIR}" \
	WLAN_RESET_KO_SYMBOL+="${STAGING_INCDIR}/mt76xx-tk-wifi-drv/Module.symvers" \
"

do_install:append() {
	install -d ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/cfg/bt.cfg ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} = " \
	${nonarch_base_libdir}/firmware/bt.cfg \
"

RPROVIDES:${PN} += " \
	kernel-module-mt7663-tk-bt \
	kernel-module-mt7668-tk-bt \
"