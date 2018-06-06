DESCRIPTION = "Set BT/WiFi on for artik533s"
SECTION = "connectivity"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
    file://bt-wifi-on.service \
    file://mrvl.conf \
"
S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE_${PN} = "bt-wifi-on.service"

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        install -m 0644 ${WORKDIR}/mrvl.conf ${D}${sysconfdir}/modules-load.d

        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/bt-wifi-on.service \
                           ${D}${systemd_unitdir}/system

        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
            -e 's,@SBINDIR@,${sbindir},g' \
            -e 's,@BINDIR@,${bindir},g' \
            -e 's,@SYS_CONFDIR@,${sysconfdir},g' \
            ${D}${systemd_unitdir}/system/bt-wifi-on.service
    fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(artik530|artik533s)"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
