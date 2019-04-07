package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.tehosiewdai.gojbboh.controller.ControllerCallback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiAsyncTask extends AsyncTask<Void, Void, String> {

    private final String TAG = ApiAsyncTask.class.getSimpleName();

    private ControllerCallback callback;

    public ApiAsyncTask(ControllerCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecute();
        }
    }

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

    @Override
    protected void onPostExecute(String results) {
        if (callback != null && results != null) {
            callback.onPostExecute(results);
        }
    }

}
