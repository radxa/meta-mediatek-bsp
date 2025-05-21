LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=https;branch=main"
SRCREV = "301e0c93a05e6f57140346de326ceb61a5ea5c25"

S = "${WORKDIR}/git"
BUILD = "${S}/${LK_BOARD_NAME}"

require lk.inc
