FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-v4l2object-add-mjpeg-decode-ym16-support.patch \
	file://0002-v4l2object-Adjust-ENCODED_BUFFER_SIZE-for-4K-video.patch \
	file://0003-v4l2object-Add-force-nonblock-poll.patch \
"
