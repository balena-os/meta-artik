DESCRIPTION = "Samsung secure bootloader for Artik 5 and Artik 10 devices"
SECTION = "bootloaders"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_artik520 = " \
    file://bl1.bin \
    file://bl2.bin \
    file://tzsw.bin \
    "

SRC_URI_artik10 = " \
    file://bl1.bin \
    file://bl2.bin \
    file://tzsw.bin \
    "

inherit deploy

RPROVIDES = "secure-boot-artik"

S = "${WORKDIR}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 755  ${S}/bl1.bin ${DEPLOYDIR}
    install -m 755  ${S}/bl2.bin ${DEPLOYDIR}
    install -m 755  ${S}/tzsw.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "(artik520|artik10)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
