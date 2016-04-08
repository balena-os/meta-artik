DESCRIPTION = "Nvram support for Broadcom BCM4354 wifi/bt device"
SECTION = "kernel"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENCE.broadcom_bcm43xx;md5=3160c14df7228891b868060e1951dfbc"

SRC_URI = " \
   file://LICENCE.broadcom_bcm43xx \
   file://brcmfmac4354-sdio.txt \
"

S = "${WORKDIR}"
BRCM_FWDIR = "/lib/firmware/brcm"

do_install() {
    install -d ${D}${BRCM_FWDIR}
    cp -r ${WORKDIR}/brcmfmac4354-sdio.txt ${D}${BRCM_FWDIR}
}

FILES_${PN} = " \
  ${BRCM_FWDIR}/brcmfmac4354-sdio.txt \
"

RDEPENDS_${PN} = "linux-firmware-bcm4354"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(artik5|artik10)"
