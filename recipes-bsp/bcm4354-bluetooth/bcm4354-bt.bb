DESCRIPTION = "Broadcom Bluetooth fw files and patch utility"
SECTION = "connectivity"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENCE.broadcom_bcm43xx;md5=3160c14df7228891b868060e1951dfbc"

SRC_URI = " \
    file://10-local.rules \
    file://BCM4354_003.001.012.0353.0745_Samsung_Artik_ORC.hcd \
    file://LICENCE.broadcom_bcm43xx \
    file://brcm-bt-firmware.service \
    file://brcm_patchram_plus \
    file://fwdown.sh \
    file://hciconf.sh \
    file://rfkill-unblock.service \
    "
S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE_${PN} = " \
    brcm-bt-firmware.service \
    rfkill-unblock.service \
    "

RDEPENDS_${PN} = " \
    bluez5 \
    "

do_install() {
    install -d  ${D}/etc/bluetooth/
    install -m 0755 ${WORKDIR}/BCM4354_003.001.012.0353.0745_Samsung_Artik_ORC.hcd \
                    ${WORKDIR}/brcm_patchram_plus \
                    ${WORKDIR}/fwdown.sh \
                    ${WORKDIR}/hciconf.sh \
                    ${D}/etc/bluetooth/

    install -d ${D}/etc/udev/rules.d/
    install -m 0755 ${WORKDIR}/10-local.rules ${D}/etc/udev/rules.d/

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/brcm-bt-firmware.service \
                           ${WORKDIR}/rfkill-unblock.service \
                           ${D}${systemd_unitdir}/system

        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
            -e 's,@SBINDIR@,${sbindir},g' \
            -e 's,@BINDIR@,${bindir},g' \
            -e 's,@SYS_CONFDIR@,${sysconfdir},g' \
            ${D}${systemd_unitdir}/system/brcm-bt-firmware.service \
            ${D}${systemd_unitdir}/system/rfkill-unblock.service
    fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(artik5|artik10)"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
