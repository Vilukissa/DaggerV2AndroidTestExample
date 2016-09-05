package com.calicode.daggertestexample.watering;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PumpControlUnit {

    @Inject
    public LowRatePump lowRatePump;
    @Inject
    public HighRatePump highRatePump;

    private WeakReference<OnPumpStateChangeListener> onPumpStateChangeListener;

    public interface OnPumpStateChangeListener {
        void onPumpStateChanged(Pump pump, boolean started);
    }

    @Inject
    public PumpControlUnit() {
    }

    public void setOnPumpStateChangeListener(OnPumpStateChangeListener onPumpStateChangeListener) {
        this.onPumpStateChangeListener = new WeakReference<>(onPumpStateChangeListener);
    }

    public void startPumps() {
        startPump(LowRatePump.class, true);
        startPump(HighRatePump.class, true);
    }

    public void stopPumps() {
        startPump(LowRatePump.class, false);
        startPump(HighRatePump.class, false);
    }

    public void startPump(Class<? extends Pump> pumpClass) {
        startPump(pumpClass, true);
    }

    public void stopPump(Class<? extends Pump> pumpClass) {
        startPump(pumpClass, false);
    }

    public Map<Class<? extends Pump>, Double> getPumpFlowRates() {
        Map<Class<? extends Pump>, Double> flowRates = new HashMap<>();
        flowRates.put(lowRatePump.getClass(), lowRatePump.flowRate());
        flowRates.put(highRatePump.getClass(), highRatePump.flowRate());
        return flowRates;
    }

    private void startPump(Class<? extends Pump> pumpClass, boolean start) {
        Pump pump = null;

        if (LowRatePump.class.equals(pumpClass)) {
            pump = lowRatePump;
        } else if (HighRatePump.class.equals(pumpClass)) {
            pump = highRatePump;
        }

        if (pump != null) {
            if (start) {
                pump.startPumping();
            } else {
                pump.stopPumping();
            }
        }

        notifyPumpStateChange(pump, start);
    }

    private void notifyPumpStateChange(Pump pump, boolean started) {
        if (onPumpStateChangeListener != null && onPumpStateChangeListener.get() != null) {
            onPumpStateChangeListener.get().onPumpStateChanged(pump, started);
        }
    }
}
