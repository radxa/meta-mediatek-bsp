LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=https;branch=main"
SRCREV = "2a1f574ed30ee6605fb0476c0a123254d3aef8a4"

S = "${WORKDIR}/git"
BUILD = "${S}/${LK_BOARD_NAME}"

require lk.inc
