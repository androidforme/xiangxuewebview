package com.wangduoyu.base.autoservice

import java.lang.Exception
import java.util.*

/**
 * 工具类
 */
class XiangxueServiceLoader {

    companion object {
        fun <S> load(service: Class<S>): S? {
            try {
                return ServiceLoader.load(service).iterator().next()
            } catch (e: Exception) {
                return null
            }
        }
    }
}