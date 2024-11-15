require mtk-apusys-prebuilt.inc

MDW_VER ?= "android13"

do_install() {
    install -d ${D}${libdir}/
    install -m 0644 ${S}/mdw/${MDW_VER}/usr/lib64/* ${D}${libdir}
}

FILES:${PN} += " \
    ${datadir} ${libdir}/* \
"