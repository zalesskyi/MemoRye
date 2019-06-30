package com.zalesskyi.android.memorye.extensions

import com.zalesskyi.android.memorye.utils.EMPTY_STRING
import com.zalesskyi.android.memorye.utils.Logger

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? =
        if (p1 == null || p2 == null) null else block(p1, p2)

fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(p1: T1? = null,
                                                    p2: T2? = null,
                                                    p3: T3? = null,
                                                    block: (T1, T2, T3) -> R?): R? =
        safeLet(p1, p2) { param1, param2 ->
            p3.takeUnless { it == null }?.let { block(param1, param2, it) }
        }

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> safeLet(p1: T1? = null,
                                                              p2: T2? = null,
                                                              p3: T3? = null,
                                                              p4: T4? = null,
                                                              block: (T1, T2, T3, T4) -> R?): R? =
        safeLet(p1, p2, p3) { param1, param2, param3 ->
            p4.takeUnless { it == null }?.let { block(param1, param2, param3, it) }
        }

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> safeLet(p1: T1? = null,
                                                                        p2: T2? = null,
                                                                        p3: T3? = null,
                                                                        p4: T4? = null,
                                                                        p5: T5? = null,
                                                                        block: (T1, T2, T3, T4, T5) -> R?): R? =
        safeLet(p1, p2, p3, p4) { param1, param2, param3, param4 ->
            p5.takeUnless { it == null }?.let { block(param1, param2, param3, param4, it) }
        }

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any> safeLet(p1: T1? = null,
                                                                                  p2: T2? = null,
                                                                                  p3: T3? = null,
                                                                                  p4: T4? = null,
                                                                                  p5: T5? = null,
                                                                                  p6: T6? = null,
                                                                                  block: (T1, T2, T3, T4, T5, T6) -> R?): R? =
        safeLet(p1, p2, p3, p4, p5) { param1, param2, param3, param4, param5 ->
            p6.takeUnless { it == null }?.let { block(param1, param2, param3, param4, param5, it) }
        }

/**
 * Print log error
 *
 * @param text [String] It`s text to print in logs
 * @param callLevel [Int] This is the level from which starts print exception tree
 *
 * @return [T] return the object from with it method was called
 */
fun <T> T?.printLogE(text: String? = EMPTY_STRING, callLevel: Int = 1) = apply {
    Logger.run {
        e(message = { "$text ${this@printLogE}" }, callStackLevel = callLevel)
    }
}

/**
 * Print log debug
 *
 * @param text [String] It`s text to print in logs
 * @param callLevel [Int] This is the level from which starts print exception tree
 *
 * @return [T] return the object from with it method was called
 */
fun <T> T?.printLog(text: String? = EMPTY_STRING, callLevel: Int = 1) = apply {
    Logger.run {
        d(message = { "$text ${this@printLog}" }, callStackLevel = callLevel)
    }
}
