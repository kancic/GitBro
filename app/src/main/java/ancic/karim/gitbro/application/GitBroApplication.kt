package ancic.karim.gitbro.application

import android.app.Application
import timber.log.Timber

class GitBroApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}