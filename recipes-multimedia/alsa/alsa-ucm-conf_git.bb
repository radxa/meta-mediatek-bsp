SUMMARY = "ALSA Use Case Manager configuration"
DESCRIPTION = "This package contains ALSA Use Case Manager configuration \
of audio input/output names and routing for specific audio hardware. \
They can be used with the alsaucm tool."
HOMEPAGE = "https://alsa-project.org"
BUGTRACKER = "https://alsa-project.org/wiki/Bug_Tracking"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20d74d74db9741697903372ad001d3b4"

SRC_URI = "git://github.com/zoran055/alsa-ucm-conf.git;branch=zoran/mt8391_0822;protocol=https"
SRCREV = "83a812fc5abe1fc30e88faac246500c525e2a359"

S = "${WORKDIR}/git"

inherit allarch

do_install() {
        install -d "${D}${datadir}/alsa"
        cp -r "${S}/ucm" "${D}${datadir}/alsa" || true
        cp -r "${S}/ucm2" "${D}${datadir}/alsa" || true
}

PACKAGES = "${PN}"

FILES:${PN} = "*"
