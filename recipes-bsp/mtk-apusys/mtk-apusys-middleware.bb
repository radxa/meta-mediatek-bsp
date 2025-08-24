require mtk-apusys-prebuilt.inc

do_install() {
    install -d ${D}${libdir}/
    install -m 0644 ${S}/mdw/${MDW_VERSION}/usr/lib64/* ${D}${libdir}
}

FILES:${PN} += " \
    ${datadir} ${libdir}/* \
"
