PACKAGECONFIG:class-target:append = " bluez-lc3 "

# HACK: Allow pipewire-pulse systemd units to run as root. Although this is
# discouraged for security reasons, currently AIoT Yocto BSP test cases are ran
# as root. This should be removed once moved to a non-root implementation.
do_install:append() {
    sed -i '/ConditionUser=!root/d' ${D}/${systemd_user_unitdir}/pipewire-pulse.service
    sed -i '/ConditionUser=!root/d' ${D}/${systemd_user_unitdir}/pipewire-pulse.socket
}

# Disable pipewire systemd as system-wide unit
SYSTEMD_SERVICE:${PN} = ""

# poky systemd.bbclass in scarthgap, lacks of support for systemd user units,
# thus enable it manually
pkg_postinst:${PN} () {
    install -d $D/$sysconfdir/systemd/user/default.target.wants
    ln -sf $systemd_user_unitdir/pipewire.service $D/$sysconfdir/systemd/user/default.target.wants/pipewire.service

    install -d $D/$sysconfdir/systemd/user/socket.target.wants
    ln -sf $systemd_user_unitdir/pipewire.socket $D/$sysconfdir/systemd/user/socket.target.wants/pipewire.socket
}

pkg_postinst:${PN}-pulse () {
    install -d $D/$sysconfdir/systemd/user/default.target.wants
    ln -sf $systemd_user_unitdir/pipewire-pulse.service $D/$sysconfdir/systemd/user/default.target.wants/pipewire-pulse.service

    install -d $D/$sysconfdir/systemd/user/socket.target.wants
    ln -sf $systemd_user_unitdir/pipewire-pulse.socket $D/$sysconfdir/systemd/user/socket.target.wants/pipewire-pulse.socket
}
