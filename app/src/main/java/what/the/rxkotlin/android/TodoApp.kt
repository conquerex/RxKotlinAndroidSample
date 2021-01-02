package what.the.rxkotlin.android

import android.app.Application

/**
 * Created by jongkook on 2021.01.02
 */
class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: TodoApp? = null
    }
}