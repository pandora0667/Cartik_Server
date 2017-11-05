package io.wisoft.rc.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Json {

  public static void parse(String jsonLine) {
    JsonElement jsonElement = new JsonParser().parse(jsonLine);
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    jsonObject = jsonObject.getAsJsonObject("data");
    JsonArray jsonArray = jsonObject.getAsJsonArray("translations");



  }
}
