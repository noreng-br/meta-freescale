# Copyright 2025 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

require imx-mcore-demos.inc

SRC_URI += "file://EULA"

FILESEXTRAPATHS:prepend := "${THISDIR}/../..:"

LIC_FILES_CHKSUM:mx95-nxp-bsp = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"

SRC_URI[imx95.sha256sum] = "1ed885757d570a1bcd4309cd41d158827f855af451d140d056fa02f92e8b73eb"

COMPATIBLE_MACHINE = "(mx95-nxp-bsp)"

copy_eula_to_source() {
    # The file is currently at .../25.12.00/sources/EULA
    # We need it at .../25.12.00/sources/imx93-m33-demo-25.12.00/EULA
    if [ -f "${WORKDIR}/sources/EULA" ]; then
        cp "${WORKDIR}/sources/EULA" "${S}/EULA"
    elif [ -f "${UNPACKDIR}/EULA" ]; then
        cp "${UNPACKDIR}/EULA" "${S}/EULA"
    else
        bbfatal "Could not find EULA at ${WORKDIR}/sources/EULA or ${UNPACKDIR}/EULA"
    fi
}

do_unpack[posfuncs] += "copy_eula_to_source"
