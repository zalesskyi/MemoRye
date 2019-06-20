package com.zalesskyi.android.memorye.providers.base

import com.zalesskyi.android.memorye.data.models.Model

abstract class BaseOnlineProvider<M : Model<*>, NetworkModule> : Provider<M> {

    val networkModule: NetworkModule = this.initNetworkModule()

    protected abstract fun initNetworkModule(): NetworkModule
}
