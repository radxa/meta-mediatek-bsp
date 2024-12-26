# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "9ddeaee0544df60b7c19e5a5684f9a96ba59e03a"
BRANCH = "mtk-v6.6-mt8395"

DEPENDS += "kernel-module-mtk-vcodec-driver-mt8395"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8395"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
