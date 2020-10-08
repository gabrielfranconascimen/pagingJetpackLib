package com.example.pagingjetpackguilda.paging2

class NetworkState
    private constructor(val status: Status, val message: String? = null) {

    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun FAILED(msg: String?) = NetworkState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}