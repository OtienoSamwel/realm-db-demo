package com.otienosamwel.realm_db_demo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class DemoApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        //init the realm db
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("demo.db")
            .allowWritesOnUiThread(false)
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)
    }
}