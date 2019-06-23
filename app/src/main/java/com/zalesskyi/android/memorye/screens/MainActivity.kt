package com.zalesskyi.android.memorye.screens

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.zalesskyi.android.memorye.R
import com.zalesskyi.android.memorye.base.BaseLifecycleActivity
import com.zalesskyi.android.memorye.base.NO_ID
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.extensions.printLog

class MainActivity : BaseLifecycleActivity<MainVM>() {
    override val viewModelClass = MainVM::class.java
    override val containerId = -1
    override val layoutId = R.layout.activity_main

    override fun getProgressBarId() = NO_ID

    override fun getSnackBarDuration() = Snackbar.LENGTH_SHORT

    private val translateObserver = Observer<TranslationSource> {
        "source: $it".printLog()
    }

    override fun observeLiveData(viewModel: MainVM) {
        with (viewModel) {
            translatorLD.observe(this@MainActivity, translateObserver)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.translate("words", Language.ENGLISH, Language.RUSSIAN)
    }
}