package com.tehosiewdai.crossthecauseway.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.tehosiewdai.crossthecauseway.controller.ControllerCallback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Async task to retrieve API results
 */
public class ApiAsyncTask extends AsyncTask<Void, Void, String> {

    /**
     * Simple name of the class.
     */
    private final String TAG = ApiAsyncTask.class.getSimpleName();

    /**
     * Controller that executed the async task.
     */
    private ControllerCallback callback;

    /**
     * Instantiates the task.
     *
     * @param callback the controller class that executes the task.
     */
    public ApiAsyncTask(ControllerCallback callback) {
        this.callback = callback;
    }

    /**
     * Task to do before executing task in background.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecute();
        }
    }

    /**
     * Tasks to do in background.
     * @param voids nothing.
     * @return result from API query.
     */
    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(callback.getUrlString());
            return NetworkUtils.getResponseFromHttpUrl(url);

        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        }
        return null;
    }

    /**
     * Task to do after getting the result.
     * @param results result from API query.
     */
    @Override
    protected void onPostExecute(String results) {
        if (callback != null && results != null) {
            callback.onPostExecute(results);
        }
    }

}
