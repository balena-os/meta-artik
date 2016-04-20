FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LINUX_VERSION = "3.10.9"

SRC_URI = " \
    git://git@bitbucket.org/rulemotion/linux-artik.git;protocol=ssh;branch=artik-next \
    file://0001-Btrfs-fix-not-being-able-to-find-skinny-extents-duri.patch \
    "

SRCREV = "4097fccd4576dd96c8cc40bb50458596e0cc58ee"

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
