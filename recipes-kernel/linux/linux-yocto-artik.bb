FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LINUX_VERSION = "3.10.9"

SRC_URI = "git://git@bitbucket.org/rulemotion/linux-artik.git;protocol=ssh;branch=artik"

SRCREV = "630456f987daef7e342917e91730e777a8032809"

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
