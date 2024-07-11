SUMMARY = "OP-TEE SAMM"
DESCRIPTION = "An application for SAMM deployment"
LICENSE = "BSD-2-Clause"

require recipes-bsp/uefi/edk2-firmware.inc

inherit python3native
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM = "file://License.txt;md5=2b415520383f7964e96700ae12b4570a"
LIC_FILES_CHKSUM += "file://edk2-platforms/License.txt;md5=2b415520383f7964e96700ae12b4570a"
SRCREV_edk2 = "3e722403cd16388a0e4044e705a2b34c841d76ca"
SRCREV_edk2-platforms = "adea4ad93c3664d879f951aea97a956c52e42ec1"

SRC_URI = "\
    ${SRC_URI_EDK2};branch=${SRCBRANCH_edk2_platforms};name=edk2;destsuffix=edk2 \
    ${SRC_URI_EDK2_PLATFORMS};branch=${SRCBRANCH_edk2};name=edk2-platforms;destsuffix=edk2/edk2-platforms \
"

# Currently pre-built tools are built from BaseTools, in some cases
# you may want to build your own tools, you can use edk2/BaseTools
# to compile them.
SRC_URI += "\
    file://GenFfs \
    file://GenFv \
    file://GenFw \
    file://GenSec \
"

PROVIDES:remove = "virtual/bootloader"
DEPENDS:append = " libgcc util-linux-libuuid"
COMPATIBLE_MACHINE = "mt*"
PATCHTOOL = "git"
S = "${WORKDIR}/edk2"
B = "${WORKDIR}/build"

SAMM_PLATFORM_DSC = "Platform/StandaloneMm/PlatformStandaloneMmPkg/PlatformStandaloneMmRpmb.dsc"
SAMM_PLATFORM = "MmStandaloneRpmb"
SAMM_BIN_NAME = "BL32_AP_MM.fd"
GENTOOLS_PATH = "${S}/BaseTools/Source/C/bin/"

do_configure:prepend() {
    mkdir -p ${GENTOOLS_PATH}
    cp ${WORKDIR}/GenFfs ${GENTOOLS_PATH}
    cp ${WORKDIR}/GenFv  ${GENTOOLS_PATH}
    cp ${WORKDIR}/GenFw  ${GENTOOLS_PATH}
    cp ${WORKDIR}/GenSec ${GENTOOLS_PATH}
}

do_compile() {
    cp ${EDK_TOOLS_PATH}/Conf/build_rule.template ${S}/Conf/build_rule.txt
    cp ${EDK_TOOLS_PATH}/Conf/tools_def.template ${S}/Conf/tools_def.txt
    cp ${EDK_TOOLS_PATH}/Conf/target.template ${S}/Conf/target.txt

    PATH="${WORKSPACE}:${BTOOLS_PATH}:$PATH" \
    build \
       --arch "${EDK2_ARCH}" \
       --buildtarget ${EDK2_BUILD_MODE} \
       --tagname ${EDK_COMPILER} \
       --platform ${SAMM_PLATFORM_DSC} \
       ${@oe.utils.parallel_make_argument(d, "-n %d")}
}

do_install() {
    mkdir -p ${D}${nonarch_base_libdir}/optee_ap/
    install -D -p -m0444 ${B}/Build/${SAMM_PLATFORM}/${EDK2_BUILD_MODE}_${EDK_COMPILER}/FV/${SAMM_BIN_NAME} ${D}${nonarch_base_libdir}/optee_ap/
}

deltask do_deploy
FILES:${PN} += " ${nonarch_base_libdir}/optee_ap/*"
