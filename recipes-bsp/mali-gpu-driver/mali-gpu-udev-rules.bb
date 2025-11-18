SUMMARY = "Shared udev rules for Mali GPU kernel driver package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://99-dma-heap.rules"

do_install () {
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/99-dma-heap.rules ${D}/${sysconfdir}/udev/rules.d/99-dma-heap.rules
}

FILES:${PN} += "${sysconfdir}/udev/rules.d/99-dma-heap.rules"
