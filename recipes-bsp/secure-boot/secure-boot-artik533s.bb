DESCRIPTION = "Samsung secure bootloader binaries for Artik 533s"
SECTION = "bootloaders"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://github.com/SamsungARTIK/boot-firmwares-artik533s.git;protocol=https;branch=A533s_os_3.2.0 \
    file://artik533s_codesigner \
    file://secureos.img \
"
SRCREV = "5a784b5b4683028c3fc1fd3180a21f2eb875330f"

inherit deploy

RPROVIDES_${PN} = "secure-boot-artik"

S = "${WORKDIR}/git"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 755  ${S}/bl1-*.img ${DEPLOYDIR}
    install -m 755  ${S}/bl_mon.img ${DEPLOYDIR}
    install -m 755  ${S}/loader-*.img ${DEPLOYDIR}
    install -m 755  ${WORKDIR}/artik533s_codesigner ${DEPLOYDIR}
    install -m 755  ${WORKDIR}/secureos.img ${DEPLOYDIR}

    # gen_nexell_image_mon
    ${WORKDIR}/artik533s_codesigner -sign ${DEPLOYDIR}/bl_mon.img

    # gen_nexell_image_secure
    ${WORKDIR}/artik533s_codesigner -sign ${DEPLOYDIR}/secureos.img
}

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "(artik533s)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
