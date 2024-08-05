FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	file://0001-include-linux-Add-MediaTek-pixel-formats.patch \
	file://0002-include-drm_fourcc-Add-MediaTek-Bayer-packing-modifi.patch \
	file://0003-libcamera-Add-support-for-MediaTek-packed-Bayer-form.patch \
	file://0004-libcamera-pipeline-simple-Add-support-for-MediaTek-i.patch \
	file://0005-libcamera-pipeline-simple-Add-support-for-Mediatek-i.patch \
"

PACKAGECONFIG:append = " gst"
EXTRA_OEMESON:append = " -Dpipelines=simple,uvcvideo"
