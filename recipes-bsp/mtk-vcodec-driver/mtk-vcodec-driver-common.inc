# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

DESCRIPTION = "Mediatek VCODEC out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRC_URI += "${AIOT_BSP_URI}/mtk-vcodec-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# Copy the Module.symvers file from ${BPN}/ to kernel-module-${BPN}/.
# The module.bbclass installs Module.symvers in ${BPN}/ but uses the
# kernel-module-${BPN}/ path in KBUILD_EXTRA_SYMBOLS.
do_install:append () {
	if [ -e "${D}${includedir}/${BPN}/Module.symvers" ] ; then
		install -Dm0644 ${D}${includedir}/${BPN}/Module.symvers \
			${D}${includedir}/kernel-module-${BPN}/Module.symvers
	fi
}
