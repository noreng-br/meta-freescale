# Copyright 2023-2025 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

require imx-mcore-demos.inc

SRC_URI += "file://EULA"

FILESEXTRAPATHS:prepend := "${THISDIR}/../..:"

LIC_FILES_CHKSUM:mx8ulp-nxp-bsp = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"

LIC_FILES_CHKSUM:mx93-nxp-bsp = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"

SRC_URI[imx8ulp.sha256sum] = "115905810d24887cf14ed081b4f52c2ca846d94ee5ba6bf459790d3151af78d2"

SRC_URI[imx93.sha256sum] = "fbaf3baa6916757747696ba90a23d515ee10215b19a03e9feb5d6b16cc4d6a4f"

COMPATIBLE_MACHINE = "(mx8ulp-nxp-bsp|mx93-nxp-bsp)"

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
