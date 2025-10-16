# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek wireless firmware"
LICENSE = "CLOSED"

inherit allarch

S = "${WORKDIR}/git/${MTK_WIRELESS_RELEASE}"

SRC_URI = "${AIOT_NDA_URI}/wireless-firmware.git;protocol=https;branch=main"
SRCREV = "7daa674af224e8af26619c979a7127c420991acb"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/../wifi.cfg ${D}${nonarch_base_libdir}/firmware/

	# MT7663
	install -m 0644 ${S}/../EEPROM_MT7663.bin ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/../TxPwrLimit_MT76x3.dat ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/mt7663/mt7663_patch_e2_hdr.bin ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/mt7663/WIFI_RAM_CODE_MT7663.bin ${D}${nonarch_base_libdir}/firmware/

	# MT7668

	install -m 0644 ${S}/../EEPROM_MT7668.bin ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/../TxPwrLimit_MT76x8.dat ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/mt7668/mt7668_patch_e2_hdr.bin ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/mt7668/WIFI_RAM_CODE_MT7668.bin ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/mt7668/WIFI_RAM_CODE2_SDIO_MT7668.bin ${D}${nonarch_base_libdir}/firmware/
}

PROVIDES = "mt7663-tk-wifi-fw mt7668-tk-wifi-fw"
PACKAGES =+ "mt76xx-tk-wifi-fw mt7663-tk-wifi-fw mt7668-tk-wifi-fw"
ALLOW_EMPTY:${PN} = "1"

RDEPENDS:mt7663-tk-wifi-fw += "mt76xx-tk-wifi-fw"
RDEPENDS:mt7668-tk-wifi-fw += "mt76xx-tk-wifi-fw"


FILES:mt76xx-tk-wifi-fw = " \
	${nonarch_base_libdir}/firmware/wifi.cfg \
"

FILES:mt7663-tk-wifi-fw = " \
	${nonarch_base_libdir}/firmware/EEPROM_MT7663.bin \
	${nonarch_base_libdir}/firmware/mt7663_patch_e2_hdr.bin \
	${nonarch_base_libdir}/firmware/TxPwrLimit_MT76x3.dat \
	${nonarch_base_libdir}/firmware/WIFI_RAM_CODE_MT7663.bin \
"

FILES:mt7668-tk-wifi-fw = " \
	${nonarch_base_libdir}/firmware/EEPROM_MT7668.bin \
	${nonarch_base_libdir}/firmware/mt7668_patch_e2_hdr.bin \
	${nonarch_base_libdir}/firmware/TxPwrLimit_MT76x8.dat \
	${nonarch_base_libdir}/firmware/WIFI_RAM_CODE_MT7668.bin \
	${nonarch_base_libdir}/firmware/WIFI_RAM_CODE2_SDIO_MT7668.bin \
"
