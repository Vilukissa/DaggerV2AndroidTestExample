package com.calicode.daggertestexample.app;

import android.app.Application;

import com.calicode.daggertestexample.inject.DaggerWateringSystem;
import com.calicode.daggertestexample.inject.DaggerWateringSystem.Builder;
import com.calicode.daggertestexample.inject.PumpModule;
import com.calicode.daggertestexample.inject.WateringSystem;
import com.calicode.daggertestexample.watering.PumpControlUnit;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class WateringApp extends Application {

    private PumpControlUnit pumpControlUnit;

    private static WateringApp sInstance;

    public static WateringApp get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        Timber.plant(new DebugTree());

        DaggerWateringSystem.Builder builder = DaggerWateringSystem.builder();
        WateringSystem wateringSystem = addModules(builder).build();
        pumpControlUnit = wateringSystem.createPumpControlUnit();
    }

    public PumpControlUnit getPumpControlUnit() {
        return pumpControlUnit;
    }

    protected DaggerWateringSystem.Builder addModules(Builder builder) {
        return builder.pumpModule(new PumpModule());
    }
}
