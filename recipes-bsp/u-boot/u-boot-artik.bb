# Samsung Artik 5 and Artik 10 u-boot

require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot which includes support for the Samsung Artik boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PROVIDES += "u-boot"

SRCREV = "436b4ae23aba34d44ac6a6f23d9f2770c6409536"

SRC_URI = " \
    git://github.com/SamsungARTIK/u-boot-artik.git;protocol=https;branch=artik-exynos/v2012.07 \
    file://0003-fix-single-sdfuse-flash-command.patch \
    "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(artik5|artik10)"

RDEPENDS_${PN} = "secure-boot-artik"
