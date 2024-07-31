FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://kmssink-0001-Add-mediatek-into-default-driver-list.patch \
"

PACKAGECONFIG:append = " kms"

