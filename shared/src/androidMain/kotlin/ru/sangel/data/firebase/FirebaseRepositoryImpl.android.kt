package ru.sangel.data.firebase

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

actual class FirebaseRepositoryImpl : FirebaseRepository, KoinComponent {
    override fun init() {
        Firebase.initialize(get() as Application)
    }
}
