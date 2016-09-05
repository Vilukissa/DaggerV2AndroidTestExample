package com.calicode.daggertestexample.inject;

import com.calicode.daggertestexample.watering.HighRatePump;
import com.calicode.daggertestexample.watering.LowRatePump;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PumpModule {

    @Provides
    @Singleton
    public LowRatePump provideLowRatePump() {
        return new LowRatePump();
    }

    @Provides
    @Singleton
    public HighRatePump provideHighRatePump() {
        return new HighRatePump();
    }
}
