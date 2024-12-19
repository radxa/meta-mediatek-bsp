# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "5d9fa68ece2fa7ec60014fa9836f645ce8cc41e9"
BRANCH = "mtk-v6.6-mt8395"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
