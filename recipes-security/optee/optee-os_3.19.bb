require optee-os-mtk.inc

PROVIDES:mtk += "virtual/optee-os"

# Add RPMB support if necessary
OPTEE_RPMB_DEV_ID ??= "0"
MACHINE_OPTEE_EARLY_TA ??= " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'uc-fde', 'optee-uc-fde', '', d)} \
"

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee-efuse', 'efuse', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee-rpmb', 'rpmb', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee-rpmb-write', 'rpmb-write', '', d)} \
    ${@oe.utils.conditional('MACHINE_OPTEE_EARLY_TA', '', '', 'early-ta', d)} \
    ${@oe.utils.conditional('OPTEE_SAMM_ENABLE', '1', 'samm rpmb', '', d)} \
    ${@oe.utils.conditional('OPTEE_SAMM_TEST', '1', 'samm-test', '', d)} \
"

PACKAGECONFIG[efuse] = "CFG_EFUSE_ENABLE=y LIBDIR=${STAGING_DIR_TARGET}/${libdir}, , libefuse-pta-prebuilt"
PACKAGECONFIG[rpmb] = "CFG_RPMB_FS=y CFG_RPMB_FS_DEV_ID=${OPTEE_RPMB_DEV_ID}"
PACKAGECONFIG[samm] = "CFG_REE_FS=n CFG_STMM_PATH=${STAGING_DIR_HOST}${nonarch_base_libdir}/optee_ap/BL32_AP_MM.fd, , optee-samm"
PACKAGECONFIG[rpmb-write] = "CFG_RPMB_WRITE_KEY=y"
PACKAGECONFIG[rpmb-test] = "CFG_RPMB_TESTKEY=y"
PACKAGECONFIG[samm-test] = "CFG_REE_FS_ALLOW_RESET=y CFG_REE_FS=y"
PACKAGECONFIG[early-ta] = "CFG_EARLY_TA=y EARLY_TA_DIR=${STAGING_DIR_HOST}${nonarch_base_libdir}/optee_armtz, , ${MACHINE_OPTEE_EARLY_TA}"

EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"
