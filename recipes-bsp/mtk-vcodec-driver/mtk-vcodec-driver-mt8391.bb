# Copyright (C) 2025 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8189: co-branch with mt8391
SRCREV = "ef949d4d91cf83c6eb9369f4b35121761ee7cb11"
BRANCH = "mtk-v6.6-mt8391"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8391"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
