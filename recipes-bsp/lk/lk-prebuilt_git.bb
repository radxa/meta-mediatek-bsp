LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=https;branch=main"
SRCREV = "ad5258502098518ad2a3b90cf8089a9e2077be9f"

S = "${WORKDIR}/git"
BUILD = "${S}/${LK_BOARD_NAME}"

require lk.inc
