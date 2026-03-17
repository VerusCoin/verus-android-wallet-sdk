package cash.z.ecc.android.sdk.model

import cash.z.ecc.android.sdk.internal.jni.RustBackend
import cash.z.ecc.android.sdk.internal.model.JniEncryptedPayload

/**
 * Channel keys used by the SDK for Verus encryption channels.
 *
 * This class is an *in-memory* container for key material returned from Rust. It is not intended
 * for long-term storage, export/import, or backup purposes.
 *
 * The underlying byte encodings are unstable and MUST NOT be exposed to users.
 */
class EncryptedPayload private constructor(
    private val ephemeralPublicKeyBytes: FirstClassByteArray,

    private val encryptedDataBytes: FirstClassByteArray,

    private val symmetricKeyBytes: FirstClassByteArray? = null
) {
    internal constructor(jni: JniEncryptedPayload) : this(
        ephemeralPublicKeyBytes = FirstClassByteArray(jni.ephemeralPublicKeyBytes.copyOf()),
        encryptedDataBytes = FirstClassByteArray(jni.encryptedDataBytes.copyOf()),
        symmetricKeyBytes = jni.symmetricKeyBytes?.let { FirstClassByteArray(it.copyOf()) }
    )

    /* copy functions are for internal use only */
    fun copyEphemeralPublicKeyBytes(): ByteArray = ephemeralPublicKeyBytes.byteArray.copyOf()

    fun copyEncryptedDataBytes(): ByteArray = encryptedDataBytes.byteArray.copyOf()

    fun copySymmetricKeyBytes(): ByteArray? = symmetricKeyBytes?.byteArray?.copyOf()

    override fun toString(): String = "EncryptedPayload(ephemeralPublicKeyBytes=***, encryptedDataBytes=***, symmetricKeyBytes=***)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedPayload

        if (ephemeralPublicKeyBytes != other.ephemeralPublicKeyBytes) return false
        if (encryptedDataBytes != other.encryptedDataBytes) return false
        if (symmetricKeyBytes != other.symmetricKeyBytes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ephemeralPublicKeyBytes.hashCode()
        result = 31 * result + encryptedDataBytes.hashCode()
        result = 31 * result + (symmetricKeyBytes?.hashCode() ?: 0)
        return result
    }

    companion object {
        suspend fun new(
            ephemeralPublicKeyBytes: ByteArray,
            encryptedDataBytes: ByteArray,
            symmetricKeyBytes: ByteArray? = null
        ): Result<EncryptedPayload> {
            val ephemeralPublicKeyCopy = ephemeralPublicKeyBytes.copyOf()
            val encryptedDataCopy = encryptedDataBytes.copyOf()
            val symmetricKeyCopy = symmetricKeyBytes?.copyOf()

            RustBackend.loadLibrary()

            return runCatching {
                // TODO: Add once implemented
                // require(RustBackend.validateEncryptedPayload(ephemeralPublicKeyCopy, encryptedDataCopy, symmetricKeyCopy))

                EncryptedPayload(
                    ephemeralPublicKeyBytes = FirstClassByteArray(ephemeralPublicKeyCopy),
                    encryptedDataBytes = FirstClassByteArray(encryptedDataCopy),
                    symmetricKeyBytes = symmetricKeyCopy?.let { FirstClassByteArray(it) }
                )
            }
        }
    }
}
