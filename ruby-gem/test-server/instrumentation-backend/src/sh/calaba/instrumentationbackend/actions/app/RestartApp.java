
package sh.calaba.instrumentationbackend.actions.app;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class RestartApp implements Action {

    private static final String TAG = "restart app";

    @Override
    public Result execute(String... args) {
        String[] className = InstrumentationBackend.LAUNCHER_ACTIVITY_FULL_CLASSNAME.split("\\.");
        InstrumentationBackend.solo.goBackToActivity(className[className.length-1]);
        return new RestartActivity().execute(args);
    }

    @Override
    public String key() {
        return "restart_app";
    }

}
