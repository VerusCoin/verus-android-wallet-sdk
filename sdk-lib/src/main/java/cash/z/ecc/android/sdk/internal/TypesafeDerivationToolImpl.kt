package cash.z.ecc.android.sdk.internal

import cash.z.ecc.android.sdk.model.Account
import cash.z.ecc.android.sdk.model.EphemeralPublicKey
import cash.z.ecc.android.sdk.model.EncryptedPayload
import cash.z.ecc.android.sdk.model.UnifiedFullViewingKey
import cash.z.ecc.android.sdk.model.UnifiedSpendingKey
import cash.z.ecc.android.sdk.model.ShieldedSpendingKey
import cash.z.ecc.android.sdk.model.SharedSecret
import cash.z.ecc.android.sdk.model.ChannelKeys
import cash.z.ecc.android.sdk.model.ZcashNetwork
import cash.z.ecc.android.sdk.tool.DerivationTool
import cash.z.ecc.android.sdk.internal.ext.Hex

internal class TypesafeDerivationToolImpl(private val derivation: Derivation) : DerivationTool {
    override suspend fun deriveUnifiedFullViewingKeys(
        seed: ByteArray,
        network: ZcashNetwork,
        numberOfAccounts: Int
    ): List<UnifiedFullViewingKey> = derivation.deriveUnifiedFullViewingKeysTypesafe(seed, network, numberOfAccounts)

    override suspend fun deriveUnifiedFullViewingKey(
        usk: UnifiedSpendingKey,
        network: ZcashNetwork
    ): UnifiedFullViewingKey = derivation.deriveUnifiedFullViewingKey(usk, network)


/*    override suspend fun deriveViewingKey(
        seed: ByteArray,
        network: ZcashNetwork
    ): ByteArray = derivation.deriveViewingKey(seed, network)
*/
    override suspend fun deriveUnifiedSpendingKey(
        transparentKey: ByteArray,
        extendedSecretKey: ByteArray,
        seed: ByteArray,
        network: ZcashNetwork,
        account: Account
    ): UnifiedSpendingKey = derivation.deriveUnifiedSpendingKey(transparentKey, extendedSecretKey, seed, network, account)


    override suspend fun deriveSaplingSpendingKey(
        seed: ByteArray,
        network: ZcashNetwork,
        account: Account
    ): ShieldedSpendingKey = derivation.deriveSaplingSpendingKey(seed, network, account)


    override suspend fun deriveUnifiedAddress(
        seed: ByteArray,
        network: ZcashNetwork,
        account: Account
    ): String = derivation.deriveUnifiedAddress(seed, network, account)

    override suspend fun deriveUnifiedAddress(
        viewingKey: String,
        network: ZcashNetwork,
    ): String = derivation.deriveUnifiedAddress(viewingKey, network)

    override suspend fun deriveShieldedAddress(
        viewingKey: String,
        network: ZcashNetwork,
    ): String = derivation.deriveShieldedAddress(viewingKey, network)

    override suspend fun deriveShieldedAddress(
        seed: ByteArray,
        network: ZcashNetwork,
        account: Account
    ): String = derivation.deriveShieldedAddress(seed, network, account)

    override suspend fun isValidShieldedAddress(
        address: String,
        network: ZcashNetwork
    ): Boolean = derivation.isValidShieldedAddress(address, network)

    override suspend fun getSymmetricKey(
        viewingKey: String,
        ephemeralPublicKey: ByteArray,
        network: ZcashNetwork
    ): String = derivation.getSymmetricKey(viewingKey, ephemeralPublicKey, network)

    override suspend fun generateSymmetricKey(
        saplingAddress: String,
        network: ZcashNetwork
    ): String = derivation.generateSymmetricKey(saplingAddress, network)

    //TODO: remove this entirely, not used and only partially implemented.
    // Biz strictly built this (partially) to get Artist started
    override suspend fun getEncryptionAddress(
        seed: ByteArray,
        fromId: ByteArray,
        toId: ByteArray,
        accountIndex: Int,
        network: ZcashNetwork
    ): String = derivation.getEncryptionAddress(seed, fromId, toId, accountIndex, network)

    override suspend fun getVerusEncryptionAddress(
        seed: ByteArray?,
        spendingKey: ByteArray?,
        fromId: ByteArray?,
        toId: ByteArray?,
        hdIndex: Int,
        encryptionIndex: Int,
        returnSecret: Boolean
    ): ChannelKeys = ChannelKeys(derivation.getVerusEncryptionAddress(seed, spendingKey, hdIndex, encryptionIndex, fromId, toId, returnSecret))

    override suspend fun encryptVerusData(
        address: ByteArray,
        encryptedData: ByteArray,
        returnSsk: Boolean
    ): EncryptedPayload = EncryptedPayload(derivation.encryptVerusDataD(address, encryptedData, returnSsk))


    override suspend fun decryptVerusData(
        ivkBytes: ByteArray?,
        ephemeralPublicKeyBytes: ByteArray?,
        encryptedData: ByteArray,
        symmetricKeyBytes: ByteArray?
    ): ByteArray {return derivation.decryptVerusDataD(ivkBytes, ephemeralPublicKeyBytes, encryptedData, symmetricKeyBytes)}
    
}
