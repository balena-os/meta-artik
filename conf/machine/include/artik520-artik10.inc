require conf/machine/include/artik.inc

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "bcm4354-bt bcm4354-nvram-config"

KERNEL_MODULE_AUTOLOAD += "dhd"
KERNEL_MODULE_PROBECONF += "dhd"
module_conf_dhd = "options dhd firmware_path=/lib/firmware/brcm/brcmfmac4354-sdio.bin nvram_path=/lib/firmware/brcm/brcmfmac4354-sdio.txt"
