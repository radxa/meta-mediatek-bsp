FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://MM21-0012-gstrawvideoparse-Fix-frame-size-calculation.patch \
	file://0001-video-format-Add-YM16-support-for-MJPEG-decode.patch \
"
