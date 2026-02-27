package cash.z.ecc.android.sdk.model

data class EncryptedPayload(
    val ephemeralPublicKey: ByteArray,
    val decrypted_data: ByteArray,
    val symmetricKey: ByteArray?
)