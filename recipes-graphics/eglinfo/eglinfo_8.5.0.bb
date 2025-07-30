SUMMARY = "Tool to report EGL info"
DESCRIPTION = "This package builds the eglinfo program in the mesa-demos package without OpenGL dependencies. \
We create a separate recipe because the mesa-demos package enforeces OpenGL dependency, \
but eglinfo does not require OpenGL actually."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/egl/opengl/eglinfo.c;beginline=1;endline=25;md5=45f205741852d8009c4779582a081d8a"

# Source tarball info is extracted from Yocto Kirkstone mesa-demos_8.5.0.bb
SRC_URI = "https://mesa.freedesktop.org/archive/demos/${PV}/mesa-demos-${PV}.tar.bz2"
SRC_URI[sha256sum] = "cea2df0a80f09a30f635c4eb1a672bf90c5ddee0b8e77f4d70041668ef71aac1"

S = "${WORKDIR}/mesa-demos-${PV}"

DEPENDS += "${@bb.utils.contains_any('GPU_LIB', 'libmali', 'virtual/libegl', '', d)} virtual/egl"
RDEPENDS:${PN} += "libegl"

# Append to CFLAGS if "x11" is not in DISTRO_FEATURES
CFLAGS:append = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', ' -DMESA_EGL_NO_X11_HEADERS', d)}"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${S}/eglinfo ${S}/src/egl/opengl/eglinfo.c -lEGL
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/eglinfo ${D}${bindir}
}

FILES:${PN} += "${bindir}/eglinfo"
