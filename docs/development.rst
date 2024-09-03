Development
===========

TFTP boot
---------

During kernel development, it can be useful to not have to reflash the board
every time you generate a new kernel image. We support TFTP kernel boot via
`u-boot`.
Only the client side is described here. The installation and configuration
of a TFTP server is not described in this document.

Enabling TFTP boot
^^^^^^^^^^^^^^^^^^

TFTP boot can be enabled from within U-Boot shell or from Linux userspace.

Enabling in Linux
~~~~~~~~~~~~~~~~~

To be able to modify the `u-boot` environment from within the Linux userspace,
you need to make sure you have included `u-boot-env` and `u-boot-fw-utils` as
part of your image.

For instance you can add the following to your `local.conf` file:

.. code::

	IMAGE_INSTALL:append = " \
		u-boot-env \
		u-boot-fw-utils \
	"

In the Linux userspace you can run the following command to enable TFTP boot:

.. prompt:: bash $

	fw_setenv force_tftpboot 1

Enabling in U-Boot
~~~~~~~~~~~~~~~~~~

To enable TFTP boot from within the `u-boot` shell, you can run the following
command:

.. prompt:: u-boot =>

	setenv force_tftpboot 1

Disabling TFTP boot
^^^^^^^^^^^^^^^^^^^

Once you want to disable TFTP boot and start booting again the kernel present
in the `kernel` partition of the mass storage, you can run one of the following
commands:

Disabling in Linux
~~~~~~~~~~~~~~~~~~

.. prompt:: bash $

	fw_setenv force_tftpboot 0

Disabling in U-Boot
~~~~~~~~~~~~~~~~~~~

.. prompt:: u-boot =>

	setenv force_tftpboot 0

Network configuration
^^^^^^^^^^^^^^^^^^^^^

U-Boot is using staticly defined IPs to connect to the TFTP server. DHCP is
not supported yet. You can see the configuration below:

+-----------------------+------------------+
| Machine               | IP               |
+=======================+==================+
| Client (U-Boot)       | 192.168.96.1/24  |
+-----------------------+------------------+
| Server (Your machine) | 192.168.96.20/24 |
+-----------------------+------------------+

Building Linux outside of Yocto
-------------------------------

When making changes to the Linux kernel, it can be useful to build it outside
of yocto. This allow to quickly do build changes without having to rebuild the
full kernel everytime. This section describes how to build a fitImage that
can be flashed or downloaded via TFTP boot without yocto.

Setting up the environment
^^^^^^^^^^^^^^^^^^^^^^^^^^

A few variables need to be defined in order to run the commands below to build
the kernel.

.. prompt:: bash $

	MACHINE=genio-1200-evk
	RITYDIR=/path/to/rity
	IMAGENAME=core-image-weston
	export ARCH=arm64
	export CROSS_COMPILE=aarch64-linux-gnu-
	export DTC_FLAGS=-@

Please modify `MACHINE`, `RITYDIR`, `IMAGE` and `CROSS_COMPILE` to fit
your setup.

Cloning Linux
^^^^^^^^^^^^^

.. prompt:: bash $

	git clone git@gitlab.com:mediatek/aiot/bsp/linux.git

Alternatively if you already have a Linux repository cloned, you can add
the `rity` remote:

.. prompt:: bash $

	 cd /path/to/linux
	 git remote add rity git@gitlab.com:mediatek/aiot/bsp/linux.git

Installing .config file
^^^^^^^^^^^^^^^^^^^^^^^

The kernel configuration used by yocto is a merge of the Linux ARM64 defconfig
and config fragments that can be found in `recipes-kernel/linux/linux-mtk`.
To install the generated yocto defconfig you can run the following commands:

.. prompt:: bash $ auto

	$ cd linux
	$ $RITYDIR/src/meta-mediatek-bsp/scripts/copy_kernel_config.sh $RITYDIR $MACHINE $IMAGENAME
	Copying kernel config to genio-1200-evk-config...
	$ mv genio-1200-evk-config .config

The commands above will retrieve the `.config` from the existing genio-1200-evk
build and copy it at the root of your linux repository.

.. note::

   Usually, the `.config` copied will contain essential modules for the board to boot.
   Since the modules are not part of the fitImage, it's often easier to just put everything in builtin
   with ``sed -i s/=m/=y/ .config``.

Generating the image-tree script
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

fitImage are created using image-tree scripts (its). A template exists
under `docs/kernel-fitimage.its`.

In order to generate the correct image-tree script for the MACHINE you are using,
please run the following command:

.. prompt:: bash $

	$RITYDIR/src/meta-mediatek-bsp/scripts/generate_kernel_its.sh $RITYDIR $MACHINE $IMAGENAME

Building the kernel fitImage
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. prompt:: bash $

	make
	mkimage -f $MACHINE.its fitImage

Flashing the kernel fitImage
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. prompt:: bash $

        pushd $RITYDIR/build/tmp/deploy/images/$MACHINE
        aiot-bootrom
        # put board in download mode
        popd
        fastboot flash kernel fitImage && fastboot reboot

.. note::

   It's possible that the generated fitImage might not fit in the ``kernel`` partition.
   In that case, run ``make defconfig`` and disable unused features such as different platforms
   in the ``Platform selection`` menu.
