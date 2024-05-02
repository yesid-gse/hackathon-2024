package com.gse.test.proyectqrhackaton.model.response

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
abstract class GeneralResponse(
    @Transient
    open var success: Boolean? = null,
    @Transient
    open var message: String? = null,
    @Transient
    open var status: Int? = null,
    @Transient
    open var code: String? = null
) : Parcelable
