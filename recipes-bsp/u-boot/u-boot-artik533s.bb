# Samsung Artik 533s u-boot

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native secure-boot-artik533s"

DESCRIPTION = "u-boot which includes support for the Samsung Artik 530s 1G (a.k.a. Artik 533s) board."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "u-boot virtual/u-boot-artik"

SRCREV = "22d4eedf9da047c77fd18ebc5a8a1d3923910dcc"
SRC_URI = "git://github.com/SamsungARTIK/u-boot-artik.git;protocol=https;branch=A533s_os_3.2.0"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(artik533s)"

# the artik533s needs the bootloader to be signed
do_compile_append() {
    #gen_nexell_image
    tools/nexell/SECURE_BINGEN -c s5p4418 -t 3rdboot -n ${S}/tools/nexell/nsih/raptor-sd.txt -i ${B}/u-boot.bin -o ${B}/bootloader.img -l 0x74c00000 -e 0x74c00000
    # sign nexell image
    ${DEPLOY_DIR_IMAGE}/artik533s_codesigner -sign ${B}/bootloader.img
}

do_deploy_append () {
    install ${B}/bootloader.img ${DEPLOYDIR}
}
