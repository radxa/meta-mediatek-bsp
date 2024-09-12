MACHINEOVERRIDES =. "mtk:"

# Kernel
KERNEL_CLASSES = "kernel-fitimage"
KERNEL_IMAGETYPE = "fitImage"
KERNEL_COMPRESSION = "gzip"

# U-Boot
UBOOT_SUFFIX = "bin"
UBOOT_ENTRYPOINT       = "0x64000000"
UBOOT_LOADADDRESS      = "0x64000000"
UBOOT_DTB_LOADADDRESS  = "0x44000000"
UBOOT_DTBO_LOADADDRESS = "0x44c00000"
UBOOT_SIGN_ENABLE = "${@bb.utils.contains('DISTRO_FEATURES', 'secure-boot', '1', '0', d)}"

# OP-TEE OS
PREFERRED_PROVIDER_virtual/optee-os ?= "optee-os"
PREFERRED_VERSION_optee-client ?= "3.19"
PREFERRED_VERSION_optee-examples ?= "3.19"
PREFERRED_VERSION_optee-os ?= "3.19"
PREFERRED_VERSION_optee-os-tadevkit ?= "3.19"
PREFERRED_VERSION_optee-test ?= "3.19"
OPTEE_TZDRAM_START ?= "0x43200000"
OPTEE_TZDRAM_SIZE  ?= "0x00a00000"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
	${@bb.utils.contains('KERNEL_IMAGETYPE', 'fitImage', '', os.path.basename("${KERNEL_DEVICETREE}"), d)} \
"

IMAGE_FSTYPES ?= "wic.img aiotflash.tar"
IMAGE_ROOTFS_ALIGNMENT = "4"
IMAGE_CLASSES += "image_type_img image_type_aiotflash"

SERIAL_CONSOLES = "921600;ttyS0"

WKS_FILE ?= "mediatek-bootimg.wks"
WIC_CREATE_EXTRA_ARGS += "-i direct-with-blksz"
WIC_SECTOR_SIZE ?= "512"
WIC_BLOCK_SIZE ?= "1024"
WICVARS:append = " WIC_SECTOR_SIZE WIC_BLOCK_SIZE"
do_image_wic[depends] += " \
	trusted-firmware-a:do_deploy \
	virtual/kernel:do_deploy \
"

PREFERRED_PROVIDER_virtual/dtb = "dtbo"
PREFERRED_VERSION_dtbo = "${@'6' if d.getVar('PREFERRED_VERSION_linux-mtk').startswith('6') else '5'}"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot"
PREFERRED_PROVIDER_virtual/lk ?= "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "lk", "lk-prebuilt", d)}"
PREFERRED_PROVIDER_virtual/bl2 ?= "bl2"

PREFERRED_VERSION_linux-mtk ??= "5.15%"

EXTRA_IMAGEDEPENDS = " \
	virtual/lk \
	${@bb.utils.contains("DISTRO_FEATURES", "security", "", "trusted-firmware-a", d)} \
	rity-tools \
"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-image"

MACHINE_EXTRA_RRECOMMENDS += " \
	kernel-modules \
"

# Set this only for factory image
# An image built with this option should not be shipped to customers
OPTEE_RPMB_WRITE_KEY ??= "0"
# Set this only to use the RPMB test key instead of generating an unique one
OPTEE_RPMB_TEST_KEY ??= "0"

MACHINE_EXTRA_RRECOMMENDS:append = " ${@bb.utils.contains("DISTRO_FEATURES", "optee-otp", " optee-otp", "", d)}"

# Set this to enable AB boot on fwupdate supported
AB_FWUPDATE_ENABLE ?= "${@bb.utils.contains("DISTRO_FEATURES", "fwupdate", "1", "0", d)}"
# Set this to sign BL2 and support DAA
BL2_SIGN_ENABLE ??= "0"
DA_SIGN_ENABLE ??= "0"

# Set this to generate the secure package required to enable secure boot
SECURE_ZIP_ENABLE ?= "${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "1", "0", d)}"
FIT_SIGN_INDIVIDUAL ?= "${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "1", "0", d)}"
FIT_GENERATE_KEYS ?= "${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "1", "0", d)}"
UBOOT_SIGN_KEYDIR ?= "${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "${DEPLOY_DIR_IMAGE}/secure", "", d)}"
UBOOT_SIGN_KEYNAME ?= "${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "u-boot", "", d)}"
UBOOT_SIGN_IMG_KEYNAME ?= "${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "u-boot-img", "", d)}"

IMAGE_CLASSES:append = "${@oe.utils.conditional('SECURE_ZIP_ENABLE', '1', ' image_type_secure_package', '', d)}"
IMAGE_FSTYPES:append = "${@oe.utils.conditional('SECURE_ZIP_ENABLE', '1', ' secure.zip', '', d)}"

# Set this to enable rootfs security for verity image
DM_VERITY_IMAGE ?= "${IMAGE_BASENAME}"
DM_VERITY_IMAGE_TYPE ?= "ext4"

# Allowed license for mtk device - overrideable if necessary
MTK_EXTRA_LICENSE_FLAGS_ACCEPTED ?= "commercial"

# Packages requiring extra licenses
LICENSE_FLAGS_ACCEPTED:append:pn-libmali = " ${MTK_EXTRA_LICENSE_FLAGS_ACCEPTED}"
