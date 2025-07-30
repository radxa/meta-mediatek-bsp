require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8195"
SOC_SRAM_ADDR = "0x201000"

# Trusted Firmware-A
TFA_PLATFORM = "mt8195"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8195"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8395-demo"

MACHINE_FEATURES:append = " optee"
MACHINE_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "optee-efuse", "", d)}"

# Mali
require gpu-provider.inc
MALI_SOC = "mt8195"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	linux-firmware-mt8195-scp \
	linux-firmware-mt8195-sof \
	${@bb.utils.contains("MACHINE_FEATURES", "optee-efuse", "optee-ewriter", "", d)} \
"

MACHINEOVERRIDES =. "mt8395:i1200:genio-1200:"
