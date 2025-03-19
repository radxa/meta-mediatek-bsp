# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-client.inc

SRCREV = "140bf463046071d3ca5ebbde3fb21ee0854e1951"
COMPATIBLE_MACHINE = "mt*"

SRC_URI += " \
    file://0001-libteeacl-condition-libteeacl-with-WITH_TEEACL.patch \
    file://70-tee-udev.rules \
"

OPTEE_WITH_TEEACL ??= "0"

EXTRA_OECMAKE:append = " -DCFG_TEE_PLUGIN_LOAD_PATH=${libdir}/tee-supplicant/plugins/"

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee-rpmb', 'rpmb', '', d)} \
"

PACKAGECONFIG[rpmb] = "-DRPMB_EMU=0, -DRPMB_EMU=1"

EXTRA_OECMAKE += " \
    -DWITH_TEEACL=${OPTEE_WITH_TEEACL} \
"

do_install:append () {
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 644 ${WORKDIR}/70-tee-udev.rules ${D}${sysconfdir}/udev/rules.d/
}
