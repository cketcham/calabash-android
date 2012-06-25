
package sh.calaba.instrumentationbackend.actions.preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;
import sh.calaba.instrumentationbackend.actions.preferences.PutPreferenceAction.PutPreferenceActionCallback;

/**
 * Puts a boolean preference
 * 
 * @author cketcham
 */
public class PutBooleanPreference implements Action, PutPreferenceActionCallback<Boolean> {

    @Override
    public Result execute(String... args) {
        return new PutPreferenceAction<Boolean>().execute(this, args);
    }

    @Override
    public String key() {
        return "put_boolean_preference";
    }

    @Override
    public Editor put(Editor editor, String key, Boolean value) {
        return editor.putBoolean(key, value);
    }

    @Override
    public Boolean get(SharedPreferences prefs, String key) {
        return prefs.getBoolean(key, false);
    }

    @Override
    public Boolean parse(String value) {
        return Boolean.valueOf(value);
    }

}
