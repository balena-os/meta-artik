FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LINUX_VERSION = "3.10.9"

SRC_URI = " \
    git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=artik-exynos/v3.10.x \
    file://0001-Btrfs-fix-not-being-able-to-find-skinny-extents-duri.patch \
    file://fix_autoload_asix_module.patch \
    "

SRCREV = "84a5d7636d3bdb0eb0c2385d973b9471d3681917"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

# for artik 5 and artik 10 we build the kernel in the source tree
B = "${S}"

do_configure_prepend() {
    rm ${B}/.config
}

COMPATIBLE_MACHINE = "(artik5|artik10)"
