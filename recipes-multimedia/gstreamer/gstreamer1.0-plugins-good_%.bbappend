FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-v4l2videoenc-set-caps-with-prepend-header-flag.patch \
	file://0002-v4l2object-add-mjpeg-decode-ym16-support.patch \
	file://0003-v4l2-bypass-check-some-transfer-types-in-colorimetry.patch \
	file://0004-v4l2object-Adjust-ENCODED_BUFFER_SIZE-for-4K-video.patch \
	file://0005-Support-dynamic-resolution-change.patch \
	file://0006-v4l2object-add-colorimetry-extensions-for-better-com.patch \
"
