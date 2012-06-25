
package sh.calaba.instrumentationbackend.actions.preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;
import sh.calaba.instrumentationbackend.actions.preferences.PutPreferenceAction.PutPreferenceActionCallback;

/**
 * Puts a string preference
 * 
 * @author cketcham
 */
public class PutStringPreference implements Action, PutPreferenceActionCallback<String> {

    @Override
    public Result execute(String... args) {
        return new PutPreferenceAction<String>().execute(this, args);
    }

    @Override
    public String key() {
        return "put_string_preference";
    }

    @Override
    public Editor put(Editor editor, String key, String value) {
        return editor.putString(key, value);
    }

    @Override
    public String get(SharedPreferences prefs, String key) {
        return prefs.getString(key, null);
    }

    @Override
    public String parse(String value) {
        return value;
    }
}
