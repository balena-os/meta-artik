FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SUMMARY = "Samsung Artik 530s 1G (a.k.a. Artik 533s) kernel"
DESCRIPTION = "artik533s machine kernel provided by Samsung"

LINUX_VERSION = "4.4.113"

SRC_URI = " \
    git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=A533s_os_3.2.0 \
"

SRCREV = "ba9767df686b18ef9ed456e85c32312cbdba2ac8"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "(artik533)"
