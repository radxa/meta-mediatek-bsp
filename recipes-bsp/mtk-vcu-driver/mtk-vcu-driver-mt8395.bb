# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "6592c350e0c2f7248b8741a70ae4dd23dc9083f0"
BRANCH = "mtk-v6.6-mt8395"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8395"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
