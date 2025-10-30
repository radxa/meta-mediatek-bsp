# poky systemd.bbclass in scarthgap, lacks of support for systemd user units,
# thus enable it manually
pkg_postinst:${PN} () {
    install -d $D/$sysconfdir/systemd/user/pipewire.service.wants
    ln -sf $systemd_user_unitdir/wireplumber.service $D/$sysconfdir/systemd/user/pipewire.service.wants/wireplumber.service
}
