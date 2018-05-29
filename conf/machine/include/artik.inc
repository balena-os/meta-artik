# Common settings for Samsung Artik boards
IMAGE_CLASSES += "artik-sdimg"

MACHINE_FEATURES += " bluetooth pci rtc ethernet serial usbgadget wifi"

KERNEL_IMAGETYPE = "zImage"
IMAGE_FSTYPES += "tar.bz2 ext4 artik-sdimg"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "boot-firmware-artik kernel-modules rfkill-unblock"
