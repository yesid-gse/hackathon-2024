package com.gse.test.proyectqrhackaton.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class AppDemoResponse (
    @SerializedName("success")
    override var success: Boolean?,
    @SerializedName("message")
    override var message : String? = null,
    @SerializedName("status")
    override var status: Int? = null,
    @SerializedName("code")
    override var code: String? = null,
): GeneralResponse(success, message, status, code)


