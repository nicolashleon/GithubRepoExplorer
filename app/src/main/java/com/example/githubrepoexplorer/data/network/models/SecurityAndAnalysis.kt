package com.example.githubrepoexplorer.data.network.models


import com.google.gson.annotations.SerializedName

data class SecurityAndAnalysis(
    @SerializedName("advanced_security")
    val advancedSecurity: AdvancedSecurity = AdvancedSecurity(),
    @SerializedName("secret_scanning")
    val secretScanning: SecretScanning = SecretScanning(),
    @SerializedName("secret_scanning_push_protection")
    val secretScanningPushProtection: SecretScanningPushProtection = SecretScanningPushProtection()
)