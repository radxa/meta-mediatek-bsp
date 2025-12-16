# Copyright (C) 2025 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8189: co-branch with mt8391
SRCREV = "dee2a3570675145b885a737a13d060d01b3f34fe"
BRANCH = "mtk-v6.6-mt8391"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8391"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
