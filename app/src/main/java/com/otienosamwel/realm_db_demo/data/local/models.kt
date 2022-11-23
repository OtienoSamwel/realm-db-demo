package com.otienosamwel.realm_db_demo.data.local

import com.otienosamwel.realm_db_demo.data.models.PostsItem
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class LocalPostItem() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var body: String = ""
    var title: String = ""
    var userId: Int = 0
}

fun LocalPostItem.toPost(): PostsItem {
    return PostsItem(body = this.body, id = this.id, title = this.title, userId = this.userId)
}