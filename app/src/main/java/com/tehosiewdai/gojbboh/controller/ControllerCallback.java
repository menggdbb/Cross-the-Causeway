package com.tehosiewdai.gojbboh.controller;

public interface ControllerCallback {
    void onPreExecute();
    void onPostExecute(String results);
    String getUrlString();
}
