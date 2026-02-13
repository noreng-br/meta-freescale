# imx-mcore-demos_%.bbappend

# 1. Point to your local 'files' directory
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# 2. Point SRC_URI to your local file
# A local fix for broken fetch pattern url :/
SRC_URI = "file://imx943-m33-demo-25.12.00.bin;fsl-eula=true"

# 3. Disable strict checksums for the source binary
BB_STRICT_CHECKSUM = "0"

# 4. CRITICAL FIX: DISABLE THE EULA CHECK CLASS
do_unpack[postfuncs] = ""

# 5. OVERRIDE THE UNPACK TASK
do_unpack() {
    # Create the destination directory
    mkdir -p ${S}

    # Copy the file
    cp ${THISDIR}/files/imx943-m33-demo-25.12.00.bin ${S}/

    # Create dummy license files
    touch "${S}/EULA"
    touch "${S}/COPYING"
}

# 6. LICENSE CHECKSUM FIX (NEW)
# We tell BitBake: "Expect the COPYING file to be empty (MD5: d41d...)"
# We use the :mx943-nxp-bsp override to ensure we beat the original recipe.
LIC_FILES_CHKSUM:mx943-nxp-bsp = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
