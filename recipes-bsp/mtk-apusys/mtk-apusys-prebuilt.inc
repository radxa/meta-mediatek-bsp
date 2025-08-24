DESCRIPTION = "Prebuilt libraries of Mediatek NeuroPilot APUSYS Middleware and Tools"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"


COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370|mt8391"

MDW_VERSION = "android13"
MDW_VERSION:mt8391 = "android15"

BRANCH = "${DISTRO_CODENAME}"

SRCREV = "3ec389cac5366a181e6999751fcf31f819ae2167"
SRC_URI += "${AIOT_RITY_URI}/mtk-neuropilot-prebuilts.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"

FILES_SOLIBSDEV = ""
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped dev-so"
