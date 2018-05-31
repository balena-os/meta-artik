DESCRIPTION = "Samsung bootloader firmware for Artik 533s"
SECTION = "bootloaders"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    file://artik533s_codesigner \
    file://secureos.img \
"

inherit deploy

S = "${WORKDIR}"

DEPENDS += "boot-firmware-artik53x"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 755  ${WORKDIR}/artik533s_codesigner ${DEPLOYDIR}
    install -m 755  ${WORKDIR}/secureos.img ${DEPLOYDIR}

    # gen_nexell_image_mon
    ${WORKDIR}/artik533s_codesigner -sign ${DEPLOY_DIR_IMAGE}/bl_mon.img

    # gen_nexell_image_secure
    ${WORKDIR}/artik533s_codesigner -sign ${DEPLOYDIR}/secureos.img
}

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "(artik533s)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
