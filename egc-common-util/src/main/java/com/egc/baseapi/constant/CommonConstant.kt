package com.egc.baseapi.constant


interface CommonConstant {
    companion object {
        /*
    result
    */
        const val SUCCESS_CODE = 200
        const val INTERNAL_SERVER_ERROR_CODE = 500
        const val INTERNAL_SERVER_ERROR = "An error occurred in service."
        const val SERVER_NOT_ERROR = "The service does not response"
        const val UNAUTHORIZED_CODE = 401
        const val UNAUTHORIZED = "Authenticated failed"
        const val BAD_REQUEST = "The input data is incorrect"
        const val INNER_USER = 1
        const val OUTER_USER = 2
        const val FREELANCER = 3
        const val REGISTER_SYSTEM = "REGISTER" // register system
        const val DEL_FLAG_1 = 1 //delete
        const val DEL_FLAG_0 = 0 //normal
        const val LOG_TYPE_1 = 1 //login
        const val LOG_TYPE_2 = 2 //action
        const val OPERATE_TYPE_1 = 1 //check
        const val OPERATE_TYPE_2 = 2 //add
        const val OPERATE_TYPE_3 = 3 //update
        const val OPERATE_TYPE_4 = 4 //delete
        const val OPERATE_TYPE_5 = 5 //import
        const val OPERATE_TYPE_6 = 6 //export
        const val EXIST_USER = 2

        //    system user type 1:backend, 2:frontend
        const val SYSTEM_TYPE_1 = "1"
        const val SYSTEM_TYPE_2 = "2"
    }
}
