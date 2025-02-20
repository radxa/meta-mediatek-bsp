SUMMARY = "MediaTek WiFi Test Tool"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_NDA_URI}/wifitest.git;protocol=https;branch=main"
SRCREV = "82841ce05ca6be98aa4da42d6548a6ab721557d6"

PV = "git${SRCPV}"

inherit autotools

S = "${WORKDIR}/git"
