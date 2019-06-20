package com.zalesskyi.android.memorye.providers.base

import com.zalesskyi.android.memorye.base.Repository
import com.zalesskyi.android.memorye.data.models.Model

abstract class BaseProvider<ModelIdType, M : Model<ModelIdType>, NetworkModule, out Repo : Repository<ModelIdType, M>>
    : BaseOnlineProvider<M, NetworkModule>() {

    val repository: Repo = this.initRepository()

    protected abstract fun initRepository(): Repo
}
