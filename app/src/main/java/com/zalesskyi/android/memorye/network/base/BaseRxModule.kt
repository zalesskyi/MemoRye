package com.zalesskyi.android.memorye.network.base

import com.zalesskyi.android.memorye.data.converters.Converter

abstract class BaseRxModule<T, NetworkModel, M>(val api: T, val converter: Converter<NetworkModel, M>)