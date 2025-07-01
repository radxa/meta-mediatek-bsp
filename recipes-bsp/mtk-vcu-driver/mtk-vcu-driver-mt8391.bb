# Copyright (C) 2025 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8189: co-branch with mt8391
SRCREV = "05d76957c3e45b73da68aa843dd77f386dd6d613"
BRANCH = "mtk-v6.6-mt8391"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8391"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8391"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
