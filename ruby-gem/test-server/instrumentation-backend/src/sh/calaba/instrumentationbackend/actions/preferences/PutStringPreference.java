package sh.calaba.instrumentationbackend.actions.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

public class PutStringPreference implements Action {

    @Override
    public Result execute(String... args) {
        SharedPreferences prefs = InstrumentationBackend.instrumentation.getTargetContext().getSharedPreferences(args[0], Context.MODE_PRIVATE);
		return prefs.edit().putString(args[1], args[2]).commit() ? Result.successResult() : new Result(false, "Preference not saved");
    }

    @Override
    public String key() {
        return "put_string_preference";
    }
}