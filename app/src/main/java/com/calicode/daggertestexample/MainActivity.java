package com.calicode.daggertestexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.calicode.daggertestexample.app.WateringApp;
import com.calicode.daggertestexample.watering.HighRatePump;
import com.calicode.daggertestexample.watering.LowRatePump;
import com.calicode.daggertestexample.watering.Pump;
import com.calicode.daggertestexample.watering.PumpControlUnit;
import com.calicode.daggertestexample.watering.PumpControlUnit.OnPumpStateChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PumpControlUnit pumpControlUnit = WateringApp.get().getPumpControlUnit();

        final TextView lowRatePumpFlowValue = (TextView) findViewById(R.id.lowRatePumpFlowValue);
        final ProgressBar lowRatePumpFlowProgress = (ProgressBar) findViewById(R.id.lowRatePumpFlowProgress);
        final TextView highRatePumpFlowValue = (TextView) findViewById(R.id.highRatePumpFlowValue);
        final ProgressBar highRatePumpFlowProgress = (ProgressBar) findViewById(R.id.highRatePumpFlowProgress);

        Switch lowRatePumpSwitch = (Switch) findViewById(R.id.lowRatePumpSwitch);
        if (lowRatePumpSwitch != null) {
            lowRatePumpSwitch.setOnCheckedChangeListener(
                    new OnPumpSwitchCheckedChangeListener(pumpControlUnit, LowRatePump.class));
        }
        Switch highRatePumpSwitch = (Switch) findViewById(R.id.highRatePumpSwitch);
        if (highRatePumpSwitch != null) {
            highRatePumpSwitch.setOnCheckedChangeListener(
                    new OnPumpSwitchCheckedChangeListener(pumpControlUnit, HighRatePump.class));
        }

        pumpControlUnit.setOnPumpStateChangeListener(new OnPumpStateChangeListener() {
            @Override
            public void onPumpStateChanged(Pump pump, boolean started) {
                if (lowRatePumpFlowProgress == null || highRatePumpFlowProgress == null) { // sanity check
                    return;
                }

                int flowRate = (int) (pump.flowRate() * 100);

                if (pump instanceof LowRatePump) {
                    lowRatePumpFlowValue.setText("" + flowRate);
                    lowRatePumpFlowProgress.setProgress(flowRate);

                } else if (pump instanceof HighRatePump) {
                    highRatePumpFlowValue.setText("" + flowRate);
                    highRatePumpFlowProgress.setProgress(flowRate);
                }
            }
        });
    }

    private static class OnPumpSwitchCheckedChangeListener implements OnCheckedChangeListener {

        private PumpControlUnit pumpControlUnit;
        private Class<? extends Pump> pumpClass;

        public OnPumpSwitchCheckedChangeListener(PumpControlUnit pumpControlUnit, Class<? extends Pump> pumpClass) {
            this.pumpControlUnit = pumpControlUnit;
            this.pumpClass = pumpClass;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                pumpControlUnit.startPump(pumpClass);
            } else {
                pumpControlUnit.stopPump(pumpClass);
            }
        }
    }
}
