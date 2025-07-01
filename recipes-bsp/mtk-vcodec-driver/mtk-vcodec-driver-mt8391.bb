# Copyright (C) 2025 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8189: co-branch with mt8391
SRCREV = "7b12cf043bf098bb4d5a4954702bd43b3b0cadb2"
BRANCH = "mtk-v6.6-mt8391"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8391"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
