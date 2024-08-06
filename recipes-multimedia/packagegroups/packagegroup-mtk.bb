# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek userspace packages"

inherit packagegroup

RDEPENDS:${PN}:mt8167 = " \
	${VIRTUAL-RUNTIME_mdpd} \
"

RDEPENDS:${PN}:mt8365 = " \
	${VIRTUAL-RUNTIME_mdpd} \
"
