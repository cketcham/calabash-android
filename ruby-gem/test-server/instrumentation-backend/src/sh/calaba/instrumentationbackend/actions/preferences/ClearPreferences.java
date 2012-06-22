package sh.calaba.instrumentationbackend.actions.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class ClearPreferences implements Action {

    @Override
    public Result execute(String... args) {
        SharedPreferences prefs = InstrumentationBackend.instrumentation.getTargetContext().getSharedPreferences(args[0], Context.MODE_PRIVATE);
		return prefs.edit().clear().commit() ? Result.successResult() : new Result(false, "Preferences not cleared");
    }

    @Override
    public String key() {
        return "clear_preferences";
    }
}