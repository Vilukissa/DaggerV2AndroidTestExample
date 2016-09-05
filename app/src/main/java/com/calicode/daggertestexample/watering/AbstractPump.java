package com.calicode.daggertestexample.watering;

public abstract class AbstractPump implements Pump {

    private final double flowRate;
    private boolean isPumping;

    public AbstractPump(double flowRate) {
        this.flowRate = flowRate;
    }

    @Override
    public void startPumping() {
        isPumping = true;
    }

    @Override
    public void stopPumping() {
        isPumping = false;
    }

    @Override
    public double flowRate() {
        return isPumping ? flowRate : 0;
    }
}
