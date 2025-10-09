LIBCAMERA_PIPELINES ??= "uvcvideo,simple"
PACKAGECONFIG:append = " gst"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
    file://0001-libcamera-formats-Add-support-for-MediaTek-packed-ba.patch \
    file://0002-pipeline-simple-Replace-mbus-sizes-range-by-default-.patch \
    file://0003-pipeline-simple-Add-support-for-MediaTek-MT8370-MT83.patch \
    file://0004-pipeline-simple-ISP-7.x-filter-mtk-cam-raw-entities.patch \
    file://0005-libcamera-v4l2_subdevice-Add-support-for-UYYVYY8_0_5.patch \
    file://0006-pipeline-simple-Add-mtk-mdp3-converter-to-mtk-seninf.patch \
"

PE = "1"
PKGV = "202406+git${SRCPV}"
