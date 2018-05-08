# Common settings for Samsung Artik boards
IMAGE_CLASSES += "artik-sdimg"

MACHINE_FEATURES += " bluetooth pci rtc ethernet serial usbgadget wifi"

KERNEL_IMAGETYPE = "zImage"
IMAGE_FSTYPES += "tar.bz2 ext4 artik-sdimg"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "bcm4354-bt bcm4354-nvram-config"
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "kernel-modules"

KERNEL_MODULE_AUTOLOAD += "dhd"
KERNEL_MODULE_PROBECONF += "dhd"
module_conf_dhd = "options dhd firmware_path=/lib/firmware/brcm/brcmfmac4354-sdio.bin nvram_path=/lib/firmware/brcm/brcmfmac4354-sdio.txt"

