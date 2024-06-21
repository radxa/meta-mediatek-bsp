# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ARM libmali"
LICENSE = "Mali"
NO_GENERIC_LICENSE[Mali] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e1263579487ef4e8ea1fcf0d282a6531"
LICENSE_FLAGS = "commercial"

inherit features_check

CONFLICT_DISTRO_FEATURES = "x11"

DEPENDS += "libdrm wayland"
DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "vulkan", "vulkan-loader", "", d)}"

PROVIDES = " \
	virtual/egl \
	virtual/libegl \
	virtual/libgbm \
	virtual/libgl \
	virtual/libgles1 \
	virtual/libgles2 \
	virtual/libgles3 \
	virtual/mesa \
"

RPROVIDES:${PN} = " \
	egl \
	libegl \
	libgbm \
	libgl \
	libgles1 \
	libgles2 \
	libgles3 \
	mesa \
	mesa-vulkan-drivers \
"

S = "${WORKDIR}/git"

BRANCH = "${DISTRO_CODENAME}"
SRC_URI = "${AIOT_RITY_URI}/libmali.git;protocol=https;branch=${BRANCH}"
SRCREV = "ed897d33a3d4d6e84c2cea516d5d1fd39bd000f3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Additional install firmware: not all GPU have firmware, only install firmware in specific GPU
REQUIRE_FIRMWARE:mt8188 = "1"
REQUIRE_FIRMWARE:mt8370 = "1"
REQUIRE_FIRMWARE:mt8195 = "0"
REQUIRE_FIRMWARE:mt8365 = "0"
REQUIRE_FIRMWARE:mt8183 = "0"

# Additional install vulkan override layer: only support it since version r36
REQUIRE_VKLAYER = "${@bb.utils.vercmp_string('${MALI_VERSION}', '35')}"


EXTRA_OEMAKE = ' \
	SOC=${MALI_SOC} \
	MALI_VERSION="r${MALI_VERSION}p0" \
	INSTALL_FIRMWARE=${REQUIRE_FIRMWARE} \
	INSTALL_VKLAYER=${REQUIRE_VKLAYER} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	if [ ${REQUIRE_FIRMWARE} = 1 ]; then
		bbplain "${MALI_SOC} requires gpu firmware"
		install -d ${D}/${nonarch_base_libdir}
		install -d ${D}/${nonarch_base_libdir}/firmware
		chown -R root:root ${D}/${nonarch_base_libdir}/firmware/
		oe_runmake install LIBDIR=${D}${libdir} INCLUDEDIR=${D}${includedir} \
			DATADIR=${D}${datadir} SYSCONFDIR=${D}${sysconfdir} FIRMWAREDIR=${D}/${nonarch_base_libdir}/firmware
	else
	oe_runmake install LIBDIR=${D}${libdir} INCLUDEDIR=${D}${includedir} \
		DATADIR=${D}${datadir} SYSCONFDIR=${D}${sysconfdir}
	fi

	chown -R root:root ${D}${libdir}/
	sed -i "s,@LIBDIR@,${libdir},g" ${D}${datadir}/vulkan/icd.d/mali.json
}

FILES:${PN} = " \
	${libdir}/*.so* \
	${sysconfdir}/OpenCL/vendors/libmali.icd \
    ${datadir}/vulkan/icd.d/mali.json \
	${@bb.utils.contains('REQUIRE_VKLAYER',  '1', '${datadir}/vulkan/implicit_layer.d/VkLayer_window_system_integration.json', '', d)} \
	${@bb.utils.contains('REQUIRE_VKLAYER',  '1', '${datadir}/vulkan/implicit_layer.d/libVkLayer_window_system_integration.so', '', d)} \
	${@bb.utils.contains('REQUIRE_FIRMWARE', '1', '${nonarch_base_libdir}/firmware/*', '', d)} \
"
FILES:${PN}-dev = "${libdir}/pkgconfig/*.pc \
                   ${datadir}/pkgconfig/*.pc \
                   ${includedir}/EGL/* \
                   ${includedir}/KHR/* \
                   ${includedir}/GLES/* \
                   ${includedir}/GLES2/* \
                   ${includedir}/GLES3/* \
                   ${includedir}/CL/* \
                   ${includedir}/CL_HPP/* \
                   ${includedir}/gbm.h \
                   ${includedir}/GL"

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped"
