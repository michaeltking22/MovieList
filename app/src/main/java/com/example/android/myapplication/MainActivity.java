package com.example.android.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String[] movies = {"JAWS", "Airplane!", "Raiders of The Lost Ark", "Ghostbusters", "Groundhog Day", "Dumb and Dumber"};
    private ArrayList<String> moviesTitle = new ArrayList<>();
    private ArrayList<String> moviesCode = new ArrayList<>();
    private ListView lv;
    private Button B1;
    private AdapterView.OnItemClickListener d;
    private ArrayAdapter<String> adapter;
    private Map<String, ?> input;
    private SharedPreferences p1;
    TextView t;
    private boolean save;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = false;
        lv = findViewById(R.id.lview);
        p1 = getPreferences(Context.MODE_PRIVATE);

        input = p1.getAll();
        moviesTitle = new ArrayList<>();
        moviesCode = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, moviesTitle);
        lv.setAdapter(adapter);
        t = findViewById(R.id.tview);
        B1 = findViewById(R.id.b1);

        for (Object o : input.values()) {
            moviesTitle.add((String) o);
        }
        for (String s : input.keySet()) {
            moviesCode.add(s);
        }
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String b = moviesCode.get(i);
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/" + b + "/"));
                startActivity(in);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final int pos = i;
                builder.setMessage("Are you sure you want to remove this movie?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        moviesCode.remove(pos);
                        moviesTitle.remove(pos);
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Warning!");
                alert.show();
                return true;
            }
        });
    }
    public void ButtonClicked(View V) {
        Intent i = new Intent(this, MovieActivity.class);
        startActivityForResult(i, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            String a = data.getStringExtra("title");
            String b = data.getStringExtra("code");
            moviesTitle.add(a);
            moviesCode.add(b);
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }



    @Override
    public void onStop() {
        super.onStop();
        Map<String, String> map = new HashMap<>();
            for(int i = 0; i<moviesTitle.size(); i++){
                map.put(moviesCode.get(i), moviesTitle.get(i));
            }
            SharedPreferences.Editor e1 = p1.edit();
            for (String s : map.keySet()) {
                e1.putString(s, map.get(s));
                e1.apply();
            }

        }

    }

