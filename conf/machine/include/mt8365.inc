require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8365"

# Trusted Firmware-A
TFA_PLATFORM = "mt8365"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8175"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8365-pumpkin"

MACHINE_FEATURES:append = " optee"
MACHINE_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "optee-efuse", "", d)}"

DISTRO_FEATURES:remove = " x11 "

# Mali
MALI_SOC = "mt8365"
MALI_VERSION ??= "48"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	${@bb.utils.contains("MACHINE_FEATURES", "optee-efuse", "optee-ewriter", "", d)} \
"

PREFERRED_PROVIDER_virtual/egl ?= "mesa"
PREFERRED_PROVIDER_virtual/libegl ?= "mesa"
PREFERRED_PROVIDER_virtual/libgbm ?= "mesa"
PREFERRED_PROVIDER_virtual/libgl ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles1 ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles2 ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles3 ?= "mesa"
PREFERRED_PROVIDER_virtual/mesa ?= "mesa"
PREFERRED_PROVIDER_virtual/opencl ?= "mesa"

VIRTUAL-RUNTIME_mdpd ?= "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "mtk-mdpd", "mdpd-prebuilt", d)}"

MACHINEOVERRIDES =. "i350:genio-350:"
