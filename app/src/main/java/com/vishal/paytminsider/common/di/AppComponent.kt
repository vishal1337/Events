package com.vishal.paytminsider.common.di

import com.vishal.paytminsider.Application
import com.vishal.paytminsider.common.di.app.AppModule
import com.vishal.paytminsider.common.di.network.NetworkModule
import com.vishal.paytminsider.common.di.ui.MainModule
import com.vishal.paytminsider.common.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        MainModule::class
    ]
)

interface AppComponent : AndroidInjector<Application> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<Application>
}