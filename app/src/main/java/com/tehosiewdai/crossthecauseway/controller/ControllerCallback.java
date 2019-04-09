package com.tehosiewdai.crossthecauseway.controller;

/**
 * Callback interface for controllers when running async tasks in the background.
 */
public interface ControllerCallback {
    /**
     * For controller class to do actions during apiAsyncTask's onPreExecute() method.
     */
    void onPreExecute();

    /**
     * For controller class to do actions during apiAsyncTask's onPostExecute() method.
     * apiAsyncTask passes result of the JSON string queried.
     *
     * @param results JSON string queried.
     */
    void onPostExecute(String results);

    String getUrlString();
}
