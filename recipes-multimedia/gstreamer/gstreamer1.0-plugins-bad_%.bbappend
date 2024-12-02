FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-kmssink-Add-mediatek-auto-detection.patch \
"

PACKAGECONFIG:append = " kms"

