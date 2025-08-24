DESCRIPTION = "Prebuilt firmwares of Mediatek APUSYS"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370|mt8391"

BRANCH:mt8395 = "mt8195"
BRANCH:mt8390 = "mt8188"
BRANCH:mt8370 = "mt8188"
BRANCH:mt8391 = "mt8189"

SRCREV:mt8395 = "2502c4bf3eabec12ef1d4a8098f419b2f1a2f6e0"
SRCREV:mt8390 = "3e31bb965867736dc19cfa8438f35e9e40ad6e60"
SRCREV:mt8370 = "3e31bb965867736dc19cfa8438f35e9e40ad6e60"
SRCREV:mt8391 = "f7ab392d47a817d6976caa800181c9c9cef1ac52"

SRC_URI += "${AIOT_BSP_URI}/mtk-apusys-firmware.git;protocol=https;branch=${BRANCH} \
           "

S = "${WORKDIR}/git"

# Match the "firmware-name" path of
# apusys.sig.img and cam_vpu1.img
# in apusys.dts
FIRMWARE_NAME:mt8395 = "mt8395"
FIRMWARE_NAME:mt8390 = "mt8390"
FIRMWARE_NAME:mt8370 = "mt8370"
FIRMWARE_NAME:mt8391 = "mt8391"

do_install() {
	# Install per-Soc firmware
	FIRMWARE_DIR=${D}/${nonarch_base_libdir}/firmware/mediatek/${FIRMWARE_NAME}
	install -d ${FIRMWARE_DIR}
	install -m 0644 ${S}/apusys.sig.img ${FIRMWARE_DIR}

	if [ "${BRANCH}" = "mt8195" ] || [ "${BRANCH}" = "mt8188" ]; then
		install -m 0644 ${S}/cam_vpu1.img ${FIRMWARE_DIR}
		install -m 0644 ${S}/cam_vpu2.img ${FIRMWARE_DIR}
		install -m 0644 ${S}/cam_vpu3.img ${FIRMWARE_DIR}
	fi
	# Match the path search rule in libneuron of NeuroPilot SDK
	NHW_DIR="${sysconfdir}/apusys/${BRANCH}"

	# Install per-SoC nhw file
	install -d ${D}/${NHW_DIR}
	install -m 0644 ${S}/nhw ${D}/${NHW_DIR}/nhw

	# Previously, libneuron loads a fixed path
	# '/etc/nhw', so we add a fallback symbolic link
	ln -sfr ${D}/${NHW_DIR}/nhw ${D}/${sysconfdir}/nhw
}

FILES:${PN} += "\
	${nonarch_base_libdir}/firmware/* \
	${sysconfdir}/*\
"
