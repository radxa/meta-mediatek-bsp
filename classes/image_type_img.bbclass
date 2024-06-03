inherit image_types

CONVERSIONTYPES += "img"
CONVERSION_CMD:img = " \
    truncate -s%4096 "${IMAGE_NAME}.${type}"; \
    echo image_type_img ; \
    echo "${IMAGE_NAME}.${type}"; \
    img2simg -b 4096 \
             -D 00000000 \
             -o "${IMAGE_NAME}.${type}.img" \
             "${IMAGE_NAME}.${type}"; \
"
CONVERSION_DEPENDS_img = "python3-pysimg-native coreutils-native"
