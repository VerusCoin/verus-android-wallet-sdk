package cash.z.ecc.android.sdk.model


data class DecryptParams(
    val ivkBytes: ByteArray?,
    val ephemeralPublicKeyHex: String?,
    val ciphertextHex: String,
    val symmetricKeyHex: String?
)