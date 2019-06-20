package com.zalesskyi.android.memorye.base

import com.zalesskyi.android.memorye.data.models.Model
import io.reactivex.Single

interface Repository<ModelIdType, T : Model<ModelIdType>> {
    fun getAll(): Single<List<T>>

    fun getById(modelId: ModelIdType): Single<T>

    fun deleteAll(): Single<Unit>

    fun delete(model: T): Single<Unit>

    fun save(model: T): Single<T>
}