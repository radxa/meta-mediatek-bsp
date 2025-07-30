# Arm Mali GPU driver & graphics library provider

# By default, Rity images come with graphics support using the Mali DDK
# driver and libmali userspace library.
#
# To use Panfrost driver and Mesa, set the following values in your conf files.
# (local.conf, machine conf or distro conf):
# GPU_DRIVER = "panfrost"
# GPU_LIB = "mesa"
GPU_DRIVER ??= "mali-gpu-driver"
GPU_LIB ??= "libmali"

# If GPU_LIB is "libmali",
# each SoC needs to define MALI_SOC and MALI_VERSION (DDK version)
MALI_SOC ??= ""
MALI_VERSION ??= "48"

# Mali DDK does not support x11
DISTRO_FEATURES:remove = " x11 "

MACHINE_EXTRA_RRECOMMENDS:append = "\
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'opengl vulkan', '${GPU_LIB}', '', d)} \
    ${@bb.utils.contains_any('GPU_DRIVER', 'mali-gpu-driver', 'mali-gpu-driver', '', d)} \
"

PREFERRED_PROVIDER_virtual/egl ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/libegl ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/libgbm ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/libgl ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/libgles1 ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/libgles2 ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/libgles3 ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/mesa ?= "${GPU_LIB}"
PREFERRED_PROVIDER_virtual/opencl ?= "${GPU_LIB}"
