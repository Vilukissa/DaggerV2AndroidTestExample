package com.calicode.daggertestexample.inject;

import com.calicode.daggertestexample.watering.BrokenTestPump;
import com.calicode.daggertestexample.watering.HighRatePump;
import com.calicode.daggertestexample.watering.LowRatePump;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PumpTestModule extends PumpModule {

    @Provides
    @Singleton
    @Override
    public LowRatePump provideLowRatePump() {
        return new BrokenTestPump();
    }

    @Provides
    @Singleton
    @Override
    public HighRatePump provideHighRatePump() {
        return new HighRatePump();
    }
}
