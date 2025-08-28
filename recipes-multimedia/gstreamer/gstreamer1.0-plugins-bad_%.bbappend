FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-kmssink-Add-mediatek-auto-detection.patch \
"

PACKAGECONFIG:append = " kms gtk3"

# gtkwaylandsink is available in 1.22
PACKAGECONFIG[gtk3] = "-Dgtk3=enabled,-Dgtk3=disabled,gtk+3"
