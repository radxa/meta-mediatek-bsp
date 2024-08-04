require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

# UBOOT_LOCALVERSION can be set to add a tag to the end of the
# U-boot version string.  such as the commit id
def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()[:10]
    except OSError:
        return None

UBOOT_LOCALVERSION = "-g${@get_git_revision('${S}')}"

SRCBRANCH ?= "mtk-v2022.10"
SRC_URI = "git://git@github.com/radxa/u-boot.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "${AUTOREV}"

SRC_URI += " \
    file://0001-Revert-cmd-pxe_utils-Check-fdtcontroladdr-in-label_b.patch \
    file://fw_env-mmc-boot.config \
    file://fw_env-ufs-boot.config \
"

python() {
    # Different boot devices require separate fw_env.config settings
    features = d.getVar('MACHINE_FEATURES', True)
    if features and 'ufs-boot' in features:
        d.setVar('UBOOT_FW_ENV_FILE', 'fw_env-ufs-boot.config')
    else:
        d.setVar('UBOOT_FW_ENV_FILE', 'fw_env-mmc-boot.config')
    bb.note(f"UBOOT_FW_ENV_FILE set to {d.getVar('UBOOT_FW_ENV_FILE')}")
}

do_configure:append () {
    if [ -e ${WORKDIR}/${UBOOT_FW_ENV_FILE} ]
    then
        ln -sf ${WORKDIR}/${UBOOT_FW_ENV_FILE} ${WORKDIR}/fw_env.config
    else
        bberror "Missing ${UBOOT_FW_ENV_FILE}"
    fi
}
