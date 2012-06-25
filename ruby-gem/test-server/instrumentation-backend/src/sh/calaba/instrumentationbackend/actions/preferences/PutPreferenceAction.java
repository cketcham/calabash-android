
package sh.calaba.instrumentationbackend.actions.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;

/**
 * Puts a string preference. Takes 4 parameters
 * 
 * <pre>
 *  0 -> preference file name
 *  1 -> key
 *  2 -> value
 * </pre>
 * 
 * @author cketcham
 */
public class PutPreferenceAction<T> {

    public interface PutPreferenceActionCallback<T> {

        /**
         * Extending classes should put the value for the key.
         * 
         * @param editor
         * @param key
         * @param value
         */
        public Editor put(Editor editor, String key, T value);

        /**
         * Return the value of the shared preferences for this key.
         * 
         * @param prefs
         * @param key
         * @return
         */
        public T get(SharedPreferences prefs, String key);

        /**
         * Parse the input value.
         * 
         * @param value
         * @return
         */
        public T parse(String value);
    }

    public final Result execute(PutPreferenceActionCallback<T> callback, String... args) {
        if (args.length < 3)
            return new Result(false, "Invalid number of arugments specified");

        SharedPreferences prefs = InstrumentationBackend.instrumentation.getTargetContext()
                .getSharedPreferences(args[0], Context.MODE_PRIVATE);

        String key = args[1];
        T value = callback.parse(args[2]);

        if (value.equals(callback.get(prefs, key)))
            return Result.successResult();

        InstrumentationBackend.activityStateChanged = true;

        return callback.put(prefs.edit(), key, value).commit() ? Result.successResult()
                : new Result(false,
                        "Preference not saved");
    }

}
