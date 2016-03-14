# Samsung Artik 5 and Artik 10 u-boot

require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot which includes support for the Samsung Artik boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PROVIDES += "u-boot"

SRCREV = "5430e192095d05a01eb610df30990b8e2c7a6104"

SRC_URI = " \
    git://git@bitbucket.org/rulemotion/u-boot-artik.git;protocol=ssh;branch=artik \
    file://0001-uboot-support-gcc5.patch \
    file://0002-use-gcc-inline-version.patch \
    file://0003-fix-single-sdfuse-flash-command.patch \
    "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(artik5|artik10)"

RDEPENDS_${PN} = "secure-boot-artik"
