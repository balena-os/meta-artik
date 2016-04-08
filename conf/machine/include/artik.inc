# Common settings for Samsung Artik boards

PREFERRED_PROVIDER_u-boot ?= "u-boot-artik"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto-artik"

MACHINE_FEATURES += " bluetooth pci rtc ethernet serial usbgadget wifi"

KERNEL_IMAGETYPE = "zImage"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "bcm4354-nvram-config"

KERNEL_MODULE_AUTOLOAD += "dhd"
KERNEL_MODULE_PROBECONF += "dhd"
module_conf_dhd = "options dhd firmware_path=/lib/firmware/brcm/brcmfmac4354-sdio.bin nvram_path=/lib/firmware/brcm/brcmfmac4354-sdio.txt"

