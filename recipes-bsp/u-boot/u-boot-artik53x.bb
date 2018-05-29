# Samsung Artik 53x u-boot (Artik 530, Artik 530s 1G - a.k.a. 533s)

require recipes-bsp/u-boot/u-boot.inc

DEPENDS_append = " bc-native dtc-native"
DEPENDS_append_artik533s = " secure-boot-artik533s"

DESCRIPTION = "u-boot which includes support for the Samsung Artik 530 and Artik 530s 1G (a.k.a. Artik 533s) boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "u-boot virtual/u-boot-artik"

SRC_URI_artik530 = "git://github.com/SamsungARTIK/u-boot-artik.git;protocol=https;branch=A530_os_3.0.0"
SRCREV_artik530 = "d9395d7378cc03d8aff9f51ae60d0ff01c651e39"

SRC_URI_artik533s = "git://github.com/SamsungARTIK/u-boot-artik.git;protocol=https;branch=A533s_os_3.2.0"
SRCREV_artik533s = "22d4eedf9da047c77fd18ebc5a8a1d3923910dcc"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(artik530|artik533s)"

FIP_LOAD_ADDR_artik530 = "0x94c00000"
FIP_LOAD_ADDR_artik533s = "0x74c00000"

# generate Nexell image
do_compile_append_artik53x() {
    #gen_nexell_image
    tools/nexell/SECURE_BINGEN -c s5p4418 -t 3rdboot -n ${S}/tools/nexell/nsih/raptor-sd.txt -i ${B}/u-boot.bin -o ${B}/bootloader.img -l ${FIP_LOAD_ADDR} -e ${FIP_LOAD_ADDR}
}

# artik533s needs the bootloader to be signed
do_compile_append_artik533s() {
    # sign nexell image
    ${DEPLOY_DIR_IMAGE}/artik533s_codesigner -sign ${B}/bootloader.img
}

do_deploy_append_artik53x () {
    install ${B}/bootloader.img ${DEPLOYDIR}
}
