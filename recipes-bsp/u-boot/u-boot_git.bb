require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

CONFIGURE_FILES = "${UBOOT_MACHINE}"

# UBOOT_LOCALVERSION can be set to add a tag to the end of the
# U-boot version string.  such as the commit id
def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()[:10]
    except OSError:
        return None

UBOOT_LOCALVERSION = "-g${@get_git_revision('${S}')}"

SRC_URI += " \
    file://0001-Revert-cmd-pxe_utils-Check-fdtcontroladdr-in-label_b.patch \
    file://fw_env-mmc-system.config.in \
    file://fw_env-ufs-system.config.in \
    file://fw_env-nor-boot.config.in \
"

python do_parse_env_size () {
    # After U-boot do_configure,
    # parse the 'CONFIG_ENV_SIZE' size to
    # generate fw_env.config in rootfs, so tools such as
    # `fw_getenv` could parse the env data correctly.

    import re
    cfg_path = os.path.join(d.getVar('B'), ".config")
    # Default (legacy) size is 0x1000 (4KB).
    env_size = 0x1000
    pattern = r'CONFIG_ENV_SIZE=(0x[0-9A-Fa-f]+)'
    with open(cfg_path, 'r') as cfg:
        for line in cfg.readlines():
            matches = re.findall(pattern, line)
            if matches:
                env_size = matches[0]
                bb.note(f"Parsed CONFIG_ENV_SIZE={env_size}")
    bb.note(f"Result env_size: {env_size}")

    # Different boot devices template require separate fw_env.config settings
    features = d.getVar('MACHINE_FEATURES', True)
    if not features:
        fw_env_template = 'fw_env-mmc-system.config.in'
    else:
        if 'nor-boot' in features:
            fw_env_template = 'fw_env-nor-boot.config.in'
        elif 'ufs-boot' in features:
            fw_env_template = 'fw_env-ufs-system.config.in'
        else:
            fw_env_template = 'fw_env-mmc-system.config.in'

    bb.note(f"fw_env.cfg template: {fw_env_template}")

    # Now open template file and update {%UBOOT_ENV_SIZE%}
    # and write to ${WORKDIR}/fw_env.config
    template_path = os.path.join(d.getVar('WORKDIR'), fw_env_template)
    config_path = os.path.join(d.getVar('WORKDIR'), "fw_env.config")
    with open(template_path, "r") as template:
        with open(config_path, "w") as fw_env:
            for line in template.readlines():
                line = line.replace("{%UBOOT_ENV_SIZE%}", env_size)
                fw_env.write(line)
            bb.note(f"Generated fw_env.cfg in {config_path}")
}

addtask parse_env_size after do_configure before do_compile
