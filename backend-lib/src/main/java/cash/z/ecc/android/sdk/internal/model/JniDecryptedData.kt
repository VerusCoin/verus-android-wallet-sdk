package cash.z.ecc.android.sdk.internal.model

import androidx.annotation.Keep

/**
 * JNI class for DecryptedData returned from Rust.
 *
 */
@Keep
class JniDecryptedData(
    val decryptedDataBytes: ByteArray,
) {
    // Override to prevent leaking key material to logs
    override fun toString(): String =
        "JniDecryptedData(decryptedDataBytes=***)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JniDecryptedData

        if (!decryptedDataBytes.contentEquals(other.decryptedDataBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = decryptedDataBytes.contentHashCode()
        return result
    }
}
