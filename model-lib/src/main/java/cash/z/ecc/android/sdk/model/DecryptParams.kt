package cash.z.ecc.android.sdk.model


data class DecryptParams(
    val ivkBytes: ByteArray?,
    val ephemeralPublicKey: ByteArray,
    val encryptedData: ByteArray,
    val symmetricKeyBytes: ByteArray?
)