LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit deploy

PROVIDES = "virtual/bl2"
SRC_URI = "file://bl2.img"

do_deploy() {
	install -m 0644 ${WORKDIR}/bl2.img ${DEPLOYDIR}/
	if [ -f "${WORKDIR}/efuse.cfg" ]; then
		install -m 0644 ${WORKDIR}/efuse.cfg ${DEPLOYDIR}/
	fi
}
addtask do_deploy after do_install
