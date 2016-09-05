package com.calicode.daggertestexample.watering;

public class LowRatePump extends AbstractPump {

    public static double LOW_FLOW_RATE = 0.36;

    public LowRatePump() {
        super(LOW_FLOW_RATE);
    }
}
