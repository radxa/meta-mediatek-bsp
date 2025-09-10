# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "2c22eb24d7a2ccb79b8a81e7f8847723a3c4c9ac"
BRANCH = "mtk-v6.6-mt8395"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8395"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
