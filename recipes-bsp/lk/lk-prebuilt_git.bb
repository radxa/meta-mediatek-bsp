LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=https;branch=main"
SRCREV = "2974631f67267c6f32c4cfd6386b425a46ef2f51"

S = "${WORKDIR}/git"
BUILD = "${S}/${LK_BOARD_NAME}"

require lk.inc

do_buildclean[noexec] = "1"
