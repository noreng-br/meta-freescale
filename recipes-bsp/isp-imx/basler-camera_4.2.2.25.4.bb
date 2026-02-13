# Copyright (C) 2020-2024 NXP

DESCRIPTION = "Basler camera binary drivers"
LICENSE = "Proprietary"

FILESEXTRAPATHS:prepend := "${THISDIR}/../..:"

LIC_FILES_CHKSUM = "file://${UNPACKDIR}/EULA;md5=a93b654673e1bc8398ed1f30e0813359 \
                    file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"

IMX_SRCREV_ABBREV = "dd86758"

inherit fsl-eula-unpack

SRC_URI = "${FSL_MIRROR}/${BPN}-${PV}-${IMX_SRCREV_ABBREV}.bin;fsl-eula=true \
           file://EULA"

SRC_URI[sha256sum] = "10b7b0e0ca6acdd4476c75b8916d2163b45ddc897bf2ec9ceb0fe4fa7cb78e98"

S = "${UNPACKDIR}/${BPN}-${PV}-${IMX_SRCREV_ABBREV}"

do_compile[noexec] = "1"

copy_eula_to_source() {
  if [ -f "${UNPACKDIR}/EULA" ]; then
    cp "${UNPACKDIR}/EULA" "${S}/EULA"
  else
    bberror "Could not find EULA file in ${UNPACKDIR} to copy!"
  fi
}

do_unpack[posfuncs] += "copy_eula_to_source"

do_install() {
    oe_runmake install INSTALL_DIR=${D}
    dest_dir=${D}/opt/imx8-isp/bin
    install -d ${D}/${libdir}
    install -d $dest_dir
    cp -r ${S}/opt/imx8-isp/bin/* $dest_dir
    cp -r ${S}/usr/lib/* ${D}/${libdir}
}

SYSTEMD_AUTO_ENABLE = "enable"

FILES:${PN} = "${libdir} /opt"
INSANE_SKIP:${PN} = "already-stripped"
RDEPENDS:${PN} += "isp-imx"

COMPATIBLE_MACHINE = "(mx8mp-nxp-bsp)"
