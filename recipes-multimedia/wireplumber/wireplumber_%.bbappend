FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://dis-vid-cap.conf \
"

do_install:append() {
    install -d ${D}${sysconfdir}/wireplumber/wireplumber.conf.d
    install -m 0644 ${WORKDIR}/dis-vid-cap.conf ${D}${sysconfdir}/wireplumber/wireplumber.conf.d/
}

FILES:${PN} += "${sysconfdir}/wireplumber/wireplumber.conf.d/dis-vid-cap.conf"

# poky systemd.bbclass in scarthgap, lacks of support for systemd user units,
# thus enable it manually
pkg_postinst:${PN} () {
    install -d $D/$sysconfdir/systemd/user/pipewire.service.wants
    ln -sf $systemd_user_unitdir/wireplumber.service $D/$sysconfdir/systemd/user/pipewire.service.wants/wireplumber.service
}
