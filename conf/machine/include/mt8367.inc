require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8367"
SOC_SRAM_ADDR = "0x2001000"

# Trusted Firmware-A
TFA_PLATFORM = "mt8391"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8391"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8391-evk"

MACHINE_FEATURES:append = " optee"
MACHINE_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "optee-efuse", "", d)}"

# Mali
require gpu-provider.inc
MALI_SOC = "mt8189"

# Temporarily removed:
#	linux-firmware-mt8188-scp
#	linux-firmware-mt8188-sof
#	${@bb.utils.contains("MACHINE_FEATURES", "optee-efuse", "optee-ewriter", "", d)}
MACHINE_EXTRA_RRECOMMENDS:append = " \
"

MACHINEOVERRIDES =. "mt8367:genio-360:"
