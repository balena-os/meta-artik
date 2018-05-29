DESCRIPTION = "BT/WiFi firmware for Samsung Artik 530 family"
SECTION = "connectivity"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
    file://WlanCalData_ext.conf \
    file://bt_cal_data.conf \
    file://bt_init_cfg.conf \
    file://sdio8977_sdio_combo.bin \
    file://sdsd8977_combo_v2.bin \
    file://txpwrlimit_cfg.bin \
"
S = "${WORKDIR}"

do_install() {
    install -d ${D}/lib/firmware/mrvl/
    install -m 0755 ${WORKDIR}/WlanCalData_ext.conf \
                    ${WORKDIR}/bt_*.conf \
                    ${WORKDIR}/sd*.bin \
                    ${WORKDIR}/txpwrlimit_cfg.bin \
                    ${D}/lib/firmware/mrvl/
}

FILES_${PN} = "/lib/firmware/mrvl/"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(artik530|artik533s)"

#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
