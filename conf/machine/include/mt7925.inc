MACHINEOVERRIDES =. "mt7925:"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "kernel-module-mt7925e linux-firmware-mt7925", "", d)} \
"
