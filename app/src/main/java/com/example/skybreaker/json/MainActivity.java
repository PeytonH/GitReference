package com.example.skybreaker.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by SkyBreaker on 2/25/2018.
 */

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    InputStream in;
    GitReference gitAdapter;
    ArrayList<commandList> commands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        try {
            in = getApplicationContext().getAssets().open("gitReference.json");
            commands = Json.populateGitReferences(Json.parseJsonToString(in));
        }
        catch(Exception e) {
            System.out.println(e);
        }

        gitAdapter = new GitReference(this, commands);
        listView.setAdapter(gitAdapter);
    }
}
