package com.zalesskyi.android.memorye.screens

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zalesskyi.android.memorye.base.BaseLifecycleVM
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.providers.ProviderInjector.translatorProvider

class MainVM(application: Application) : BaseLifecycleVM(application) {

    val translatorLD = MutableLiveData<TranslationSource>()

    fun translate(text: String, from: Language, to: Language) {
        translatorProvider.translateWord(from, to, text)
                .doAsync(translatorLD)
    }
}