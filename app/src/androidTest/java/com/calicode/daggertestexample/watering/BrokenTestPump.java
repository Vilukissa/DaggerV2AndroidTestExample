package com.calicode.daggertestexample.watering;

public class BrokenTestPump extends LowRatePump {

    public static final double BROKEN_FLOW_RATE = 0.04;

    @Override
    public double flowRate() {
        return BROKEN_FLOW_RATE;
    }
}
