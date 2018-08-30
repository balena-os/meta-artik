SUMMARY = "Samsung Artik 530 and Artik 530s 1G (a.k.a. Artik 533s) kernel"
DESCRIPTION = "artik530 and artik533s machine kernel provided by Samsung"

LINUX_VERSION_artik530 = "4.4.113"
LINUX_VERSION_artik533s = "4.4.113"

SRC_URI_artik530 = "git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=A530-OS-18.05.00"
SRCREV_artik530 = "release/A530s-OS-18.05.00"

SRC_URI_artik533s = "git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=A533s-OS-18.05.00"
SRCREV_artik533s = "release/A533s-OS-18.05.00"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "(artik530|artik533s)"
