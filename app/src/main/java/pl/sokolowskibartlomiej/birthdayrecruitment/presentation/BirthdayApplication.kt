package pl.sokolowskibartlomiej.birthdayrecruitment.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.sokolowskibartlomiej.birthdayrecruitment.di.mainModule

class BirthdayApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@BirthdayApplication)
            modules(mainModule)
        }
    }
}