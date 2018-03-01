package com.example.skybreaker.json;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by SkyBreaker on 2/25/2018.
 */

public class Json {

    public static String parseJsonToString(InputStream is) {
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<commandList> populateGitReferences(String jsonString) {
        ArrayList<commandList> data = new ArrayList<>();

        JSONArray jArray = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            jArray = jsonObject.getJSONArray("commands");

            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                commandList commands = new commandList();
                commands.setExplanation(json_data.getString("explanation"));
                Log.i("JSON", "Adding: " + commands.getExplanation());
                commands.setCommand(json_data.getString("command"));
                Log.i("JSON", "Adding: " + commands.getCommand());
                commands.setExample(json_data.getString("example"));
                Log.i("JSON", "Adding: " + commands.getExample());
                commands.setSection(json_data.getString("section"));
                Log.i("JSON", "Adding: " + commands.getSection());

                data.add(commands);
            }

        } catch (Exception ex) {

        }

        return data;
    }

    public static String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    public static boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE); // TODO: Your code goes here
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean append(Context context, String fileName, String jsonString) {
        // TODO: Your code here

        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] strBytes = jsonString.getBytes();
            fos.write(strBytes);

            fos.close();

        } catch (FileNotFoundException fnfe){
            return false;
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}