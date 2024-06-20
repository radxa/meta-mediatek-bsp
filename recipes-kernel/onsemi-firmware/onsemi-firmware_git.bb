# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ONSemiconductor firmware"

LICENSE = "Firmware-ONSemi"
LIC_FILES_CHKSUM = "file://LICENSE.pdf;md5=d10282f155f2c17fd5ce100c459c3c84"
NO_GENERIC_LICENSE[Firmware-ONSemi] = "LICENSE.pdf"

inherit allarch

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ONSemiconductor/ap1302_binaries.git;protocol=https;branch=main"
SRCREV = "c9b818574bdc5cb09679c95732bf1ebc505c533a"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_license_rename() {
	cp "${S}/AP1302 Software License Agreement.pdf" ${S}/LICENSE.pdf
}
addtask do_license_rename after do_unpack before do_populate_lic

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	if [ "${ONSEMI_BOARD_NAME}" = "OLogic_Pumpkin_i500" ]; then
		install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0330_single_fw.bin" \
			${D}${nonarch_base_libdir}/firmware
		install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0144_single_fw.bin" \
			${D}${nonarch_base_libdir}/firmware
		install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0144_dual_fw.bin" \
			${D}${nonarch_base_libdir}/firmware
                install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0430_single_fw.bin" \
                        ${D}${nonarch_base_libdir}/firmware
                install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar1335_single_fw.bin" \
                        ${D}${nonarch_base_libdir}/firmware
	elif [ "${ONSEMI_BOARD_NAME}" = "MediaTek_AIoT_i350_EVK" ]; then
		install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0430_single_fw.bin" \
			${D}${nonarch_base_libdir}/firmware
	fi
}

PACKAGES =+ "${PN}-ap1302-ar0330"
PACKAGES =+ "${PN}-ap1302-ar0144"
PACKAGES =+ "${PN}-ap1302-ar0144-dual"
PACKAGES =+ "${PN}-ap1302-ar0430"
PACKAGES =+ "${PN}-ap1302-ar1335"
ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-ap1302-ar0330 = "${nonarch_base_libdir}/firmware/ap1302_ar0330_single_fw.bin"
FILES:${PN}-ap1302-ar0144 = "${nonarch_base_libdir}/firmware/ap1302_ar0144_single_fw.bin"
FILES:${PN}-ap1302-ar0144-dual = "${nonarch_base_libdir}/firmware/ap1302_ar0144_dual_fw.bin"
FILES:${PN}-ap1302-ar0430 = "${nonarch_base_libdir}/firmware/ap1302_ar0430_single_fw.bin"
FILES:${PN}-ap1302-ar1335 = "${nonarch_base_libdir}/firmware/ap1302_ar1335_single_fw.bin"

