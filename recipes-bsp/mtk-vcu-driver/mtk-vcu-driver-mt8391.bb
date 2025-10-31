# Copyright (C) 2025 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8189: co-branch with mt8391
SRCREV = "82edf385a1d045e0d32d481fd3578a1ba667cdd9"
BRANCH = "mtk-v6.6-mt8391"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8391"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8391"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
