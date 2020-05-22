package com.vishal.paytminsider.common.di.ui

import com.vishal.paytminsider.common.di.config.qualifier.PerActivity
import com.vishal.paytminsider.common.di.ui.events.EventsFragmentModule
import com.vishal.paytminsider.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            EventsFragmentModule::class
        ]
    )
    abstract fun provideMainActivity(): MainActivity

}