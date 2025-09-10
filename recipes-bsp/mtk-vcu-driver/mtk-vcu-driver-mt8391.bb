# Copyright (C) 2025 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8189: co-branch with mt8391
SRCREV = "c0610329d3317207e67c2cb0b1e6b6dd693618fd"
BRANCH = "mtk-v6.6-mt8391"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8391"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8391"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
