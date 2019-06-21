package com.zalesskyi.android.memorye.screens

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zalesskyi.android.memorye.base.BaseLifecycleVM
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.network.modules.NetworkModule

class MainVM(application: Application) : BaseLifecycleVM(application) {

    private val translatorModule = NetworkModule.client.translator

    val translatorLD = MutableLiveData<TranslationSource>()

    fun translate(text: String, from: Language, to: Language) {
        translatorModule.translateSentence(to, text)
                .doAsync(translatorLD)
    }
}