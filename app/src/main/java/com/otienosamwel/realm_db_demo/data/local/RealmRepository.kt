package com.otienosamwel.realm_db_demo.data.local

import com.otienosamwel.realm_db_demo.data.models.PostsItem
import io.realm.Realm

object RealmRepository {
    private val db = Realm.getDefaultInstance()

    fun insertPost(postItem: PostsItem) {
        db.executeTransactionAsync() {
            val dbPost = LocalPostItem().apply {
                body = postItem.body
                id = postItem.id
                title = postItem.title
                userId = postItem.userId
            }
            it.insert(dbPost)
        }
    }

    fun getLocalPosts(): List<PostsItem> {
        return db.where(LocalPostItem::class.java).findAll().toPostList()
    }
}


fun List<PostsItem>.insertPosts() {
    this.forEach {
        RealmRepository.insertPost(it)
    }
}

fun List<LocalPostItem>.toPostList(): List<PostsItem> {
    return this.map { it.toPost() }
}