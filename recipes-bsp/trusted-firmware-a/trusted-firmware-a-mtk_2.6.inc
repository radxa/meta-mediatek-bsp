require trusted-firmware-a-mtk.inc

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=https"
SRCREV_tfa = "2d9cd4119c6a995ce80668d5f1dc58179db4c223"

SRC_URI:append:mt8370 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8390 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8395 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI += "file://0002-feat-build-add-support-for-new-binutils-versions.patch"
SRC_URI += "file://rot_key.pem"
