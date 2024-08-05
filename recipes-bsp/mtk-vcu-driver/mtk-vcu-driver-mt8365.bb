# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8365: co-branch with mt8167
SRCREV = "82cb21d24b7154956eaac4f4992bea73d911d9ea"
BRANCH = "mtk-v6.6-mt8167"

EXTRA_OEMAKE += "TARGET_PLATFORM=mt8365"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

PROVIDES += "kernel-module-${PN}"
RPROVIDES:${PN} += "kernel-module-${PN}"
