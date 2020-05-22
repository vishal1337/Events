package com.vishal.paytminsider.common.di.ui.events

import com.vishal.paytminsider.common.di.config.qualifier.PerFragment
import com.vishal.paytminsider.ui.events.EventsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class EventsFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun provideEventsFragment(): EventsFragment

}