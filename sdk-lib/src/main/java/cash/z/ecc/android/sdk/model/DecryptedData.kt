package cash.z.ecc.android.sdk.model

import cash.z.ecc.android.sdk.internal.jni.RustBackend
import cash.z.ecc.android.sdk.internal.model.JniDecryptedData

/**
 * Channel keys used by the SDK for Verus encryption channels.
 *
 * This class is an *in-memory* container for key material returned from Rust. It is not intended
 * for long-term storage, export/import, or backup purposes.
 *
 * The underlying byte encodings are unstable and MUST NOT be exposed to users.
 */
class DecryptedData private constructor(
    private val decryptedDataBytes: FirstClassByteArray,
) {
    internal constructor(jni: JniDecryptedData) : this(
        decryptedDataBytes = FirstClassByteArray(jni.decryptedDataBytes.copyOf()),
    )

    /* copy functions are for internal use only */
    fun copyDecryptedDataBytes(): ByteArray = decryptedDataBytes.byteArray.copyOf()

    override fun toString(): String = "DecryptedData(decryptedDataBytes=***)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DecryptedData

        if (decryptedDataBytes != other.decryptedDataBytes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = decryptedDataBytes.hashCode()
        return result
    }

    companion object {
        suspend fun new(
            decryptedDataBytes: ByteArray,
        ): Result<DecryptedData> {
            val encryptedDataCopy = decryptedDataBytes.copyOf()

            RustBackend.loadLibrary()

            return runCatching {
                // TODO: Add once implemented
                // require(RustBackend.validateDecryptedData(ephemeralPublicKeyCopy, encryptedDataCopy, symmetricKeyCopy))

                DecryptedData(
                    decryptedDataBytes = FirstClassByteArray(encryptedDataCopy),
                )
            }
        }
    }
}
