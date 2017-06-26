FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LINUX_VERSION = "3.10.104"

SRC_URI = " \
    git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=artik-exynos/v3.10.x \
    file://0001-Btrfs-fix-not-being-able-to-find-skinny-extents-duri.patch \
    file://0002-mali-Fix-out-of-tree-compile-errors.patch \
    "

SRC_URI_append_kitra520 = " file://0003-Add-support-for-kitra520.patch"

SRCREV = "1f6a3a15adb16a94e2f967c73d5ab35a113bd438"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "(artik5|artik10|kitra520)"
