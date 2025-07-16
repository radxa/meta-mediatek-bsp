require trusted-firmware-a-mtk.inc

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=https"
SRCREV_tfa = "b0af61a01d70e0486e9f54d4c3c8985834b360bc"

SRC_URI:append:mt8370 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8390 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8395 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI += "file://0002-feat-build-add-support-for-new-binutils-versions.patch"
SRC_URI += "file://rot_key.pem"
