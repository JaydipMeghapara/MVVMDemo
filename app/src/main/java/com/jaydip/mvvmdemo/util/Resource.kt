package com.jaydip.mvvmdemo.util


/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/05/20, 2:34 PM.
 */
data class Resource<out T>(var status: Status, val data: T?, val message: String?) {



    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

}