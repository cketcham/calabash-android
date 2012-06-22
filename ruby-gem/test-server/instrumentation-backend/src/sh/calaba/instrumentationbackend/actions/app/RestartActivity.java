
package sh.calaba.instrumentationbackend.actions.app;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.IntentFilter;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class RestartActivity implements Action {

    private static final String TAG = "RestartActivity";

    @Override
    public Result execute(String... args) {
        Activity activity = InstrumentationBackend.solo.getCurrentActivity();
        InstrumentationBackend.solo.goBack();
        InstrumentationBackend.solo.goBack();

        if (activity != null) {
            // Start theh activity again and finish the old one
            IntentFilter filter = null;
            ActivityMonitor monitor = InstrumentationBackend.instrumentation.addMonitor(filter, null, false);
            
            activity.finish();
            activity.startActivity(activity.getIntent());
            
            InstrumentationBackend.instrumentation.waitForMonitor(monitor);
            InstrumentationBackend.instrumentation.waitForIdleSync();
            return Result.successResult();
        } else {
            return new Result(false, "Unable to get current activity");
        }
    }

    @Override
    public String key() {
        return "restart_activity";
    }

}
