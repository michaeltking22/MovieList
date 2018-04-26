package com.example.android.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by android on 3/29/18.
 */


public class MovieActivity extends AppCompatActivity {

    private EditText e1;
    private EditText e2;
    private String s1;
    private String s2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_view);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        s1 = "";
        s2 = "";

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                s1 = editable.toString();
            }
        });

        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                s2 = editable.toString();
            }
        });

    }

        @Override
        public void onBackPressed(){
            Intent result = new Intent();
            result.putExtra("title", s1);
            result.putExtra("code", s2);
            setResult(RESULT_OK, result);
            finish();
        }



    }

