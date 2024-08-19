# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-os.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "a120426f1e58d710f26e6e492205ff0f0721d6e6"
SRC_URI = "${AIOT_BSP_URI}/optee-os.git;protocol=https;branch=mtk-v3.19"

SRC_URI += " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
	file://0002-allow-setting-sysroot-for-clang.patch \
	file://0003-core-add-support-for-EARLY_TA_DIR.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

# set dram size
EXTRA_OEMAKE += " \
        CFG_DRAM_SIZE=${OPTEE_DRAM_SIZE} \
        CFG_TZDRAM_START=${OPTEE_TZDRAM_START} \
        CFG_TZDRAM_SIZE=${OPTEE_TZDRAM_SIZE} \
        SOC_PLATFORM=${SOC_FAMILY} \
"

EXTRA_OEMAKE += "${@oe.utils.conditional("DEBUG", "1", "DEBUG=1 CFG_TEE_CORE_LOG_LEVEL=3 CFG_UART_ENABLE=y", \
                                                       "DEBUG=0 CFG_TEE_CORE_LOG_LEVEL=0 CFG_UART_ENABLE=n CFG_DEBUG_INFO=n", d)}"
