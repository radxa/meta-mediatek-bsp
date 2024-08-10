# scp image for FROMLIST MDP3 driver
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://mt8195/scp.img \
        file://mt8188/scp.img \
	file://sof/sof-mt8188.ri \
	file://sof/sof-mt8188.ldc \
	file://sof-tplg/sof-mt8188.tplg \
	file://sof/sof-mt8195.ri \
	file://sof/sof-mt8195.ldc \
	file://sof-tplg/sof-mt8195.tplg \
"

do_install:append() {
	install -d ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8195
	install -d ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8188
	install -d ${D}/${nonarch_base_libdir}/firmware/mediatek/sof
	install -d ${D}/${nonarch_base_libdir}/firmware/mediatek/sof-tplg
	install -m 0644 ${WORKDIR}/mt8195/scp.img ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8195
	install -m 0644 ${WORKDIR}/mt8188/scp.img ${D}/${nonarch_base_libdir}/firmware/mediatek/mt8188
	install -m 0644 ${WORKDIR}/sof/sof-mt8188.ri ${D}/${nonarch_base_libdir}/firmware/mediatek/sof
	install -m 0644 ${WORKDIR}/sof/sof-mt8188.ldc ${D}/${nonarch_base_libdir}/firmware/mediatek/sof
	install -m 0644 ${WORKDIR}/sof-tplg/sof-mt8188.tplg ${D}/${nonarch_base_libdir}/firmware/mediatek/sof-tplg
	install -m 0644 ${WORKDIR}/sof/sof-mt8195.ri ${D}/${nonarch_base_libdir}/firmware/mediatek/sof
	install -m 0644 ${WORKDIR}/sof/sof-mt8195.ldc ${D}/${nonarch_base_libdir}/firmware/mediatek/sof
	install -m 0644 ${WORKDIR}/sof-tplg/sof-mt8195.tplg ${D}/${nonarch_base_libdir}/firmware/mediatek/sof-tplg
}

# packages for image installation
FILES:${PN}-mt8183-scp = "${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img"
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

FILES:${PN}-mt8188-sof = " \
	${nonarch_base_libdir}/firmware/mediatek/sof/sof-mt8188.ri \
	${nonarch_base_libdir}/firmware/mediatek/sof/sof-mt8188.ldc \
	${nonarch_base_libdir}/firmware/mediatek/sof-tplg/sof-mt8188.tplg \
"

FILES:${PN}-mt8195-sof = " \
	${nonarch_base_libdir}/firmware/mediatek/sof/sof-mt8195.ri \
	${nonarch_base_libdir}/firmware/mediatek/sof/sof-mt8195.ldc \
	${nonarch_base_libdir}/firmware/mediatek/sof-tplg/sof-mt8195.tplg \
"

FILES:${PN}-rtl8852be = " \
	${nonarch_base_libdir}/firmware/rtw89/rtw8852b_fw-1.bin \
	${nonarch_base_libdir}/firmware/rtw89/rtw8852b_fw.bin \
	${nonarch_base_libdir}/firmware/rtl_bt/rtl8852bu_config.bin \
	${nonarch_base_libdir}/firmware/rtl_bt/rtl8852bu_fw.bin \
"

PACKAGES =+ "${PN}-mt8183-scp"
PACKAGES =+ "${PN}-mt8195-scp"
PACKAGES =+ "${PN}-mt8188-scp"
PACKAGES =+ "${PN}-mt7668"
PACKAGES =+ "${PN}-mt7663"
PACKAGES =+ "${PN}-mt7961"
PACKAGES =+ "${PN}-mt8188-sof"
PACKAGES =+ "${PN}-mt8195-sof"
PACKAGES =+ "${PN}-rtl8852be"
