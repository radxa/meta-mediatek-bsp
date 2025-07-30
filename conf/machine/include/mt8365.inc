require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8365"
SOC_SRAM_ADDR = "0x201000"

# Trusted Firmware-A
TFA_PLATFORM = "mt8365"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8175"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8365-pumpkin"

MACHINE_FEATURES:append = " optee"
MACHINE_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "optee-efuse", "", d)}"

# Mali
require gpu-provider.inc
MALI_SOC = "mt8365"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	linux-firmware-mt8365-sof \
	${@bb.utils.contains("MACHINE_FEATURES", "optee-efuse", "optee-ewriter", "", d)} \
"

VIRTUAL-RUNTIME_mdpd ?= "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "mtk-mdpd", "mdpd-prebuilt", d)}"

MACHINEOVERRIDES =. "i350:genio-350:"
