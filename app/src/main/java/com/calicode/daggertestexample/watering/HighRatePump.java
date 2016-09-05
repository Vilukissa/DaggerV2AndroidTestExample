package com.calicode.daggertestexample.watering;

public class HighRatePump extends AbstractPump {

    public static final double HIGH_FLOW_RATE = 0.82;

    public HighRatePump() {
        super(HIGH_FLOW_RATE);
    }
}
