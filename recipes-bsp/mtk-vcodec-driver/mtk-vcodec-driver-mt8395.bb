# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "d2b2b47e9139f4b76413aa23da51d57d030ed537"
BRANCH = "mtk-v6.6-mt8395"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
