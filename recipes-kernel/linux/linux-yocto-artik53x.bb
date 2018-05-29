SUMMARY = "Samsung Artik 530 and Artik 530s 1G (a.k.a. Artik 533s) kernel"
DESCRIPTION = "artik530 and artik533s machine kernel provided by Samsung"

LINUX_VERSION_artik530 = "4.4.71"
LINUX_VERSION_artik533s = "4.4.113"

SRC_URI_artik530 = "git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=A530_os_3.0.0"
SRCREV_artik530 = "cfca92a8ac59e446e39d7f263fec88abc9cec9ae"

SRC_URI_artik533s = "git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=A533s_os_3.2.0"
SRCREV_artik533s = "ba9767df686b18ef9ed456e85c32312cbdba2ac8"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "(artik530|artik533s)"
