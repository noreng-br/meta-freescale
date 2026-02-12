# Copyright 2017-2025 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

require imx-mcore-demos.inc

SRC_URI += "file://EULA"

FILESEXTRAPATHS:prepend := "${THISDIR}/../..:"

LIC_FILES_CHKSUM:mx8mm-nxp-bsp = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"

LIC_FILES_CHKSUM:mx8mq-nxp-bsp = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"

SRC_URI[imx8mm.sha256sum] = "b2a08b5d5aeb23ffb9d30f915a551c891c997d6ed55f12a9e21103b6153752cc"
SRC_URI[imx8mq.sha256sum] = "9cd1e2e1f328911ea7fb13cd4da213a2ca0d08108963ca501c694edad100ec3f"

COMPATIBLE_MACHINE = "(mx8mm-nxp-bsp|mx8mq-nxp-bsp)"

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
