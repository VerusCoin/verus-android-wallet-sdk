package cash.z.ecc.android.sdk.internal.model

import androidx.annotation.Keep

/**
 * JNI class for EncryptedPayload returned from Rust.
 *
 */
@Keep
class JniEncryptedPayload(

    val ephemeralPublicKeyBytes: ByteArray,

    val encryptedDataBytes: ByteArray,

    val symmetricKeyBytes: ByteArray? = null,
) {
    // Override to prevent leaking key material to logs
    override fun toString(): String =
        "JniEncryptedPayload(ephemeralPublicKeyBytes=***, encryptedDataBytes=***, symmetricKeyBytes=${if (symmetricKeyBytes != null) "***" else "null"})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JniEncryptedPayload

        if (!ephemeralPublicKeyBytes.contentEquals(other.ephemeralPublicKeyBytes)) return false

        if (!encryptedDataBytes.contentEquals(other.encryptedDataBytes)) return false

        // handle nullable byte arrays safely
        if (symmetricKeyBytes != null) {
            if (other.symmetricKeyBytes == null) return false
            if (!symmetricKeyBytes.contentEquals(other.symmetricKeyBytes)) return false
        } else if (other.symmetricKeyBytes != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ephemeralPublicKeyBytes.contentHashCode()
        result = 31 * result + encryptedDataBytes.contentHashCode()
        result = 31 * result + (symmetricKeyBytes?.contentHashCode() ?: 0)
        return result
    }
}
