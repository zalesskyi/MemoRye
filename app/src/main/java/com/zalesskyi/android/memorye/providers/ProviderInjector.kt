package com.zalesskyi.android.memorye.providers

object ProviderInjector {
    val translatorProvider by lazy { TranslatorProviderImpl() }
}