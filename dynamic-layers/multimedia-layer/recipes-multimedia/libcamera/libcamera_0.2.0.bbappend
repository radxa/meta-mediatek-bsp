SRC_URI = "${AIOT_BSP_URI}/libcamera.git;protocol=https;branch=mtk-aiot-multi-stream"
SRCREV = "5265f50e0b6ac08f12297059a95b980584db0e57"

PV = "202111+git${SRCPV}"

FILES:${PN}-dev += " ${libdir}/libcamera*.so"

PACKAGECONFIG[lc-compliance] = "-Dlc-compliance=enabled,-Dlc-compliance=disabled"
PACKAGECONFIG:append = " gst"

CXXFLAGS += "-Wno-error=deprecated-declarations"
