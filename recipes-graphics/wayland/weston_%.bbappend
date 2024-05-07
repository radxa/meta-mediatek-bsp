SIMPLECLIENTS = "${@bb.utils.contains("PREFERRED_PROVIDER_virtual/mesa", "imgtec-pvr", "damage,im,egl,shm,touch,dmabuf-v4l,dmabuf-egl", "all", d)}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-HACK-mediatek-weston-add-clear-mode-flow-for-hotplug.patch"
