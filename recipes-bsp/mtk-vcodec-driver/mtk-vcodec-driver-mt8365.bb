# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8365: co-branch with mt8167
SRCREV = "9d08e18f38fd38551b8ce1ea4a718df344849f9f"
BRANCH = "mtk-v6.6-mt8167"

DEPENDS += "kernel-module-mtk-vcu-driver-mt8365"
EXTRA_OEMAKE += "TARGET_PLATFORM=mt8365"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
