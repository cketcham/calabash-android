package sh.calaba.instrumentationbackend.actions.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class RemovePreference implements Action {

    @Override
    public Result execute(String... args) {
        SharedPreferences prefs = InstrumentationBackend.instrumentation.getTargetContext().getSharedPreferences(args[0], Context.MODE_PRIVATE);
		return prefs.edit().remove(args[1]).commit() ? Result.successResult() : new Result(false, "Preference not removed");
    }

    @Override
    public String key() {
        return "remove_preference";
    }
}