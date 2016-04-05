# Common settings for Samsung Artik boards

PREFERRED_PROVIDER_u-boot ?= "u-boot-artik"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto-artik"

MACHINE_FEATURES += " bluetooth pci rtc ethernet serial usbgadget wifi"

KERNEL_IMAGETYPE = "zImage"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "bcm4354-nvram-config"

