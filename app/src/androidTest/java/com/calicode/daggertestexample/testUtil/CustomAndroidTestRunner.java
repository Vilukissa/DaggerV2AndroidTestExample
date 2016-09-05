package com.calicode.daggertestexample.testUtil;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.calicode.daggertestexample.app.WateringTestApp;

public class CustomAndroidTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return Instrumentation.newApplication(WateringTestApp.class, context);
    }
}
