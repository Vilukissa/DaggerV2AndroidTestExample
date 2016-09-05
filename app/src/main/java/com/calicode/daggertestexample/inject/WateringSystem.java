package com.calicode.daggertestexample.inject;

import com.calicode.daggertestexample.watering.PumpControlUnit;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = PumpModule.class)
@Singleton
public interface WateringSystem {
    PumpControlUnit createPumpControlUnit();
}
