DESCRIPTION = "Samsung bootloader firmware for Artik 530 boards (530, 530s, 533 - a.k.a. 530 1G , 533s - a.k.a 530s 1G)"
SECTION = "bootloaders"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_artik530 = "git://github.com/SamsungARTIK/boot-firmwares-artik530.git;protocol=https;branch=A530_os_3.0.0"
SRCREV_artik530 = "aad334982dab5c7ac9c212b531da80c1c3a45397"

SRC_URI_artik533s = "git://github.com/SamsungARTIK/boot-firmwares-artik533s.git;protocol=https;branch=A533s_os_3.2.0"
SRCREV_artik533s = "5a784b5b4683028c3fc1fd3180a21f2eb875330f"

inherit deploy

RPROVIDES_${PN} = "boot-firmware-artik"

S = "${WORKDIR}/git"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 755  ${S}/bl1-*.img ${DEPLOYDIR}
    install -m 755  ${S}/bl_mon.img ${DEPLOYDIR}
    install -m 755  ${S}/loader-*.img ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "(artik530|artik533s)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
