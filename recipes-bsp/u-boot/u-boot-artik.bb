# Samsung Artik 5 and Artik 10 u-boot

require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot which includes support for the Samsung Artik boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PROVIDES += "u-boot"

SRCREV = "436b4ae23aba34d44ac6a6f23d9f2770c6409536"

SRC_URI = " \
    git://github.com/SamsungARTIK/u-boot-artik.git;protocol=https;branch=artik-exynos/v2012.07 \
    file://0001-compiler-gcc6.h-Add-support-for-GCC6.patch \
    file://0002-fix-single-sdfuse-flash-command.patch \
    "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(artik520|artik10)"

RDEPENDS_${PN} = "secure-boot-artik"
ROOTPART ?= "2"

do_compile_append() {
    cp `find . -name "env_common.o"` copy_env_common.o
    ${OBJCOPY} -O binary --only-section=.rodata `find . -name "copy_env_common.o"`
    tr '\0' '\n' < copy_env_common.o | grep '=' | tee default_envs_emmc.txt default_envs_sd.txt > /dev/null

    # Set root partition's  number:
    # exit with code 1 when no replacement done so we error out when upstream modifies the u-boot env
    sed -i "/rootpart=[0-9]/{s//rootpart=${ROOTPART}/;h};\${x;/./{x;q0};x;q1}" default_envs_emmc.txt
    sed -i "/rootpart=[0-9]/{s//rootpart=${ROOTPART}/;h};\${x;/./{x;q0};x;q1}" default_envs_sd.txt

    # eMMC boots will default to "run mmcboot"
    sed -i "s/bootcmd=.*/bootcmd=run mmcboot/g" default_envs_emmc.txt

    # root device should be (mmcblk)1 when booting the SD card flasher image (exit with code 1 when no replacement done so we error out when upstream modifies the u-boot env)
    sed -i '/rootdev=[0-9]\{1\}/{s//rootdev=1/;h};${x;/./{x;q0};x;q1}' default_envs_sd.txt

    # add rootdelay for SD card boot (exit code 1 when no change so we error out when upstream modifies the u-boot env)
    sed -i '/loglevel=[0-9]\{1\}/{s//& rootdelay=3/;h};${x;/./{x;q0};x;q1}' default_envs_sd.txt

    # flasher image needs u-boot env file instructions for u-boot to flash the bootloader components for future eMMC boot
    sed -i "s/bootcmd=.*/bootcmd=sdfuse format;sdfuse flash 1 fwbl1 bl1.bin;sdfuse flash 1 bl2 bl2.bin;sdfuse flash 1 bootloader u-boot.bin;sdfuse flash 1 tzsw tzsw.bin;run mmcboot/g" default_envs_sd.txt

    tools/mkenvimage -s 16384 -o params_emmc.bin default_envs_emmc.txt
    tools/mkenvimage -s 16384 -o params_sd.bin default_envs_sd.txt
}

do_deploy_append () {
    install ${B}/params_emmc.bin ${DEPLOYDIR}
    install ${B}/params_sd.bin ${DEPLOYDIR}
}
