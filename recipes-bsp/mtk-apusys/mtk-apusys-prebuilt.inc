DESCRIPTION = "Prebuilt libraries of Mediatek NeuroPilot APUSYS Middleware and Tools"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370"

BRANCH = "${DISTRO_CODENAME}"

SRCREV = "d53a85e72b0ba7f0ea7665270e5e95e479d20d7f"
SRC_URI += "${AIOT_RITY_URI}/mtk-neuropilot-prebuilts.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

MDW_VERSION="android13"

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"

FILES_SOLIBSDEV = ""
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped dev-so"