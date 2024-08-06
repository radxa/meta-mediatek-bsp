FILESEXTRAPATHS:prepend := "${THISDIR}/mdpd:"

RDEPENDS:${PN} += "libmdp-prot libmdppq"
DEPENDS = "libmdp-prot libmdppq"

inherit systemd
inherit update-rc.d

SRC_URI += " \
	file://mdpd.service \
	file://mdpd.sh \
"

do_install:append() {
	install -d ${D}${bindir}
	install -m 755 ${B}/mdpd ${D}${bindir}

	install -d ${D}${libdir}
	install -m 644 ${B}/libmdpd.so ${D}${libdir}
	install -m 644 ${B}/libmdp.mt*.so ${D}${libdir}


	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/mdpd.service ${D}${systemd_unitdir}/system/
	else
		install -d ${D}${sysconfdir}/init.d
		install -m 0755 ${WORKDIR}/mdpd.sh ${D}${sysconfdir}/init.d/mdpd
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'mdpd.service', '', d)}"

INITSCRIPT_NAME = "mdpd"

FILES:${PN} += " \
	${bindir}/mdpd \
	${libdir}/libmdpd.so \
	${libdir}/libmdp.mt*.so \
	${sysconfdir}/init.d/mtpd \
	${sysconfdir}/systemd/system/multi-user.target.wants/mdpd.service \
	${systemd_unitdir}/system/mdpd.service \
"

INSANE_SKIP:${PN}-dev +=" dev-elf "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
