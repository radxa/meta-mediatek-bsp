# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "799b05cf6644cc43e738b0c2609a1816d27bb02d"
BRANCH = "mtk-v6.6-mt8395"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
