VESPER_MIC_CONFIG ??= '3_1'

INTERNAL_OR_PIN_HEADER = "${@bb.utils.contains('I300_PUMPKIN_AUDIO_CONF', 'i2s', '40-pins-header' , 'internalmic', d)}"
MIC_CONFIG = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper-hat-6-1' if VESPER_MIC_CONFIG == '6_1' else 'vesper-hat-3-1', '${INTERNAL_OR_PIN_HEADER}', d)}"

FILESEXTRAPATHS:prepend:i300-pumpkin := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS:prepend:i300a-sb30 := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS:prepend:mt8365-evk := "${THISDIR}/mt8365-evk:"
FILESEXTRAPATHS:prepend:mt8365-sb35 := "${THISDIR}/mt8365-sb35:"
FILESEXTRAPATHS:prepend:mt8365-pumpkin := "${THISDIR}/mt8365-pumpkin:"
FILESEXTRAPATHS:prepend:genio-700-evk := "${THISDIR}/mt8390-evk:"
FILESEXTRAPATHS:prepend:mt8195-demo := "${THISDIR}/mt8195-demo:"
FILESEXTRAPATHS:prepend:genio-1200-evk := "${THISDIR}/mt8395-evk:"
FILESEXTRAPATHS:prepend:genio-1200-evk-p1v1 := "${THISDIR}/mt8395-evk-p1v1:"
FILESEXTRAPATHS:prepend:genio-510-evk := "${THISDIR}/mt8370-evk:"
