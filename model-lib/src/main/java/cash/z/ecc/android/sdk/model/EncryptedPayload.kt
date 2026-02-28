package cash.z.ecc.android.sdk.model

data class EncryptedPayload(
    val ephemeralPublicKey: ByteArray,
    val encrypted_data: ByteArray,
    val symmetricKey: ByteArray?
)