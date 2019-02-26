SUMMARY = "Samsung Artik 530 and Artik 530s 1G (a.k.a. Artik 533s) kernel"
DESCRIPTION = "artik530 and artik533s machine kernel provided by Samsung"

FILESEXTRAPATHS_append := ":${THISDIR}/files"

SRC_URI_append = " \
    file://0004-bq2429xx_Enable_charging.patch \
    "

LINUX_VERSION_artik530 = "4.4.113"
LINUX_VERSION_artik533s = "4.4.159"

SRC_URI_artik530 = "git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=A530-OS-18.05.00"
SRCREV_artik530 = "release/A530s-OS-18.05.00"

SRC_URI_artik533s = "git://github.com/SamsungARTIK/linux-artik.git;protocol=https;branch=ARTIK-SW-18.10.00"
SRCREV_artik533s = "75f01d3be5d04083a27a87aa9b3edd79a6495c95"

require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "(artik530|artik533s)"

# compile the device tree overlays
do_compile_append() {
    oe_runmake dtbs CC="${KERNEL_CC} " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
}

do_install_append() {
    mkdir -p ${D}/boot/overlays
    install -m 0644 ${B}/arch/arm/boot/dts/overlays/*.dtbo ${D}/boot/overlays
}

FILES_kernel-devicetree_append = " /${KERNEL_IMAGEDEST}/overlays/*"

do_deploy_append() {
    install -d ${DEPLOYDIR}/overlays/
    cp ${D}/boot/overlays/* ${DEPLOYDIR}/overlays
}
