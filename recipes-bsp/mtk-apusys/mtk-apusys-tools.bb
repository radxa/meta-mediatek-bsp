require mtk-apusys-prebuilt.inc

MDW_VER ?= "android13"

do_install() {
	install -d ${D}${sbindir}/
	install -m 0755 ${S}/mdw/${MDW_VER}/usr/sbin/* ${D}${sbindir}
}

RDEPENDS:${PN} = "mtk-apusys-middleware"

FILES:${PN} += " \
	${datadir} ${sbindir}/apu_mdw_test \
	${datadir} ${sbindir}/edma_test \
	${datadir} ${sbindir}/mdla2_player \
	${datadir} ${sbindir}/mdla3_player \
	${datadir} ${sbindir}/vpu5_test \
"