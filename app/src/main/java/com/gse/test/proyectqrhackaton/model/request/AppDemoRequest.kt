package com.gse.test.proyectqrhackaton.model.request

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class AppDemoRequest(
    @SerializedName("urlDemo") var urlDemo: String? = null,
) : Parcelable