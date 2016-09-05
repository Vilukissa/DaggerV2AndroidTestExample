package com.calicode.daggertestexample.app;

import com.calicode.daggertestexample.inject.DaggerWateringSystem;
import com.calicode.daggertestexample.inject.DaggerWateringSystem.Builder;
import com.calicode.daggertestexample.inject.PumpTestModule;

public class WateringTestApp extends WateringApp {

    @Override
    protected DaggerWateringSystem.Builder addModules(Builder builder) {
        return builder.pumpModule(new PumpTestModule());
    }
}
