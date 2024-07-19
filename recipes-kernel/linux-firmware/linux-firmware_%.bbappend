# scp image for FROMLIST MDP3 driver
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://mt8195/scp.img \
        file://mt8188/scp.img \
"

do_install:append() {
	install -d ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8195
	install -d ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8188
	install -m 0644 ${WORKDIR}/mt8195/scp.img ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8195
	install -m 0644 ${WORKDIR}/mt8188/scp.img ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8188
}

# packages for image installation
FILES:${PN}-mt8195-scp = "${nonarch_base_libdir}/firmware/mediatek/mt8195/scp.img"
FILES:${PN}-mt8188-scp = "${nonarch_base_libdir}/firmware/mediatek/mt8188/scp.img"
FILES:${PN}-mt7668 = "${nonarch_base_libdir}/firmware/mediatek/mt7668pr2h.bin"
FILES:${PN}-mt7663 = " \
	${nonarch_base_libdir}/firmware/mediatek/mt7663_n9_rebb.bin \
	${nonarch_base_libdir}/firmware/mediatek/mt7663_n9_v3.bin \
	${nonarch_base_libdir}/firmware/mediatek/mt7663pr2h.bin \
	${nonarch_base_libdir}/firmware/mediatek/mt7663pr2h_rebb.bin \
"
FILES:${PN}-mt7961 = " \
	${nonarch_base_libdir}/firmware/mediatek/WIFI_MT7961_patch_mcu_1_2_hdr.bin \
	${nonarch_base_libdir}/firmware/mediatek/WIFI_RAM_CODE_MT7961_1.bin \
	${nonarch_base_libdir}/firmware/mediatek/BT_RAM_CODE_MT7961_1_2_hdr.bin \
"

PACKAGES =+ "${PN}-mt8195-scp"
PACKAGES =+ "${PN}-mt8188-scp"
PACKAGES =+ "${PN}-mt7668"
PACKAGES =+ "${PN}-mt7663"
PACKAGES =+ "${PN}-mt7961"
