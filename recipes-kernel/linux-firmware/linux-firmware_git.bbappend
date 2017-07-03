FILESEXTRAPATHS_append := ":${THISDIR}/files"

SRC_URI += " file://brcmfmac4354-sdio.bin"

do_install_append() {
    # use the wifi firmware Samsung delivered in the release A520_OS_2.0.0-sdfuse.img
    cp ${WORKDIR}/brcmfmac4354-sdio.bin ${D}/lib/firmware/brcm/
}
