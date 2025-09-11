FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-video-format-Add-YM16-support-for-MJPEG-decode.patch \
    file://0002-gst-launch-accept-option-to-set-program-name.patch \
"
