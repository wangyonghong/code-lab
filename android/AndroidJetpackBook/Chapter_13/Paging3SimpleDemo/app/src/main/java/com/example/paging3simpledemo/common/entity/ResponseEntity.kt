package com.example.paging3simpledemo.common.entity

class ResponseEntity {

    var page = 0
    var per_page = 0
    var total = 0
    var total_pages = 0
    var data: List<SingleUserData>? = null

    class SingleUserData {
        var id = 0
        var email: String? = null
        var first_name: String? = null
        var last_name: String? = null
        var avatar: String? = null
    }
}