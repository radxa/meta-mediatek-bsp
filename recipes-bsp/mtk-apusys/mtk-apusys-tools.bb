require mtk-apusys-prebuilt.inc

do_install() {
	install -d ${D}${sbindir}/
	install -m 0755 ${S}/mdw/${MDW_VERSION}/usr/sbin/* ${D}${sbindir}
}

RDEPENDS:${PN} = "mtk-apusys-middleware"

FILES:${PN} += " \
    ${datadir} ${sbindir}/* \
"

