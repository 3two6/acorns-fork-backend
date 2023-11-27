package com.egc.bs.entity.user



open class UserState {

    companion object{
        open val APPLY=0
        open var PERMITTED=1
        open var FORBIDDEN=2
    }

}