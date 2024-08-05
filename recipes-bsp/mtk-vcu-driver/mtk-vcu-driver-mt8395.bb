# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "5db2c763f97a8e698107304f01f02a9f89431a62"
BRANCH = "mtk-v6.6-mt8395"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8395"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
