
package sh.calaba.instrumentationbackend.actions.app;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.IntentFilter;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

/**
 * Restarts an activity only if the state of the activity has changed. It can be
 * forced to restart anyway if true is passed as the first argument
 * 
 * @author cketcham
 */
public class RestartActivity implements Action {

    private static final String TAG = "RestartActivity";

    @Override
    public Result execute(String... args) {

        // If the state of the activity didn't change and we don't want to force
        // restart the activity
        if (!InstrumentationBackend.activityStateChanged
                && (args.length < 0 || !Boolean.valueOf(args[0])))
            return Result.successResult();

        InstrumentationBackend.activityStateChanged = false;

        Activity activity = InstrumentationBackend.solo.getCurrentActivity();
        InstrumentationBackend.solo.goBack();
        InstrumentationBackend.solo.goBack();

        if (activity != null) {
            // Start the activity again and finish the old one
            IntentFilter filter = null;
            ActivityMonitor monitor = InstrumentationBackend.instrumentation.addMonitor(filter,
                    null, false);

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
