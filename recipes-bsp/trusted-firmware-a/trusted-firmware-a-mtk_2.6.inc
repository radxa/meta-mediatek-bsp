require trusted-firmware-a-mtk.inc

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=https"
SRCREV_tfa = "bc95ec7cd439aa16611282c82ceb337f01c77674"

SRC_URI:append:mt8370 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8390 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8395 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI += "file://0002-feat-build-add-support-for-new-binutils-versions.patch"
SRC_URI += "file://rot_key.pem"
