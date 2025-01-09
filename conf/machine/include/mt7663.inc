MTK_WIRELESS_RELEASE ?= "alps-release-r0.mp5-aiot"
MTK_WIRELESS_CHIP = "MT7663"
MACHINEOVERRIDES =. "mt7663:"

MT7663_WIFI_USE_UPSTREAM_DRV ??= "${@bb.utils.contains('DISTRO_FEATURES', 'nda-mtk', '0', '1', d)}"

WIFI_OSS_PKGS = "kernel-module-mt7663s mt7663-service"
WIFI_NDA_PKGS = "kernel-module-mt7663-tk-wifi wifitest"
WIFI_PKGS = "${@bb.utils.contains('MT7663_WIFI_USE_UPSTREAM_DRV', '1', '${WIFI_OSS_PKGS}', '${WIFI_NDA_PKGS}', d)}"

MT7663_BT_USE_UPSTREAM_DRV ??= "${@bb.utils.contains('DISTRO_FEATURES', 'nda-mtk', '0', '1', d)}"

BT_OSS_PKGS = "kernel-module-btmtksdio linux-firmware-mt7663"
BT_NDA_PKGS = "kernel-module-mt7663-tk-bt mtk-bluetooth boots picus bt-vendor"
BT_PKGS = "${@bb.utils.contains('MT7663_BT_USE_UPSTREAM_DRV', '1', '${BT_OSS_PKGS}', '${BT_NDA_PKGS}', d)}"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	${@bb.utils.contains('DISTRO_FEATURES', 'wifi', '${WIFI_PKGS}', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', '${BT_PKGS}', '', d)} \
"
