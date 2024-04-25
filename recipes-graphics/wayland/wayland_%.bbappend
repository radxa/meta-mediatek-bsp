
do_install:append () {
	if [ ${PREFERRED_PROVIDER_virtual/mesa} = "libmali" ] && [ ${MALI_VERSION} -lt 47 ]; then
	  bbplain "remove Wayland provided libwayland-egl library in favour of the version in libmali"
	  rm -f ${D}${libdir}/libwayland-egl*
	  rm -f ${D}${libdir}/pkgconfig/wayland-egl.pc
	else
	  bbplain "use Wayland provided libwayland-egl library"
	fi
}
