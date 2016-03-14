# Common settings for Samsung Artik boards

PREFERRED_PROVIDER_u-boot ?= "u-boot-artik"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto-artik"

MACHINE_FEATURES += " pci rtc ethernet serial usbgadget usbhost"

KERNEL_IMAGETYPE = "zImage"
