package com.example.edibleflowers.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.edibleflowers.R;

/**
 * @author 65667
 */
public class FlowerActivity extends AppCompatActivity {

    private TextView flowerTitle, flowerIntroduce, flowerValue, flowerPart;

    private String name, introduce, value, part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);

        flowerTitle = findViewById(R.id.flower_title);
        flowerIntroduce = findViewById(R.id.flower_introduce);
        flowerValue = findViewById(R.id.flower_value);
        flowerPart = findViewById(R.id.flower_part);

        name = getIntent().getStringExtra("name");
        introduce = getIntent().getStringExtra("introduce");
        value = getIntent().getStringExtra("value");
        part = getIntent().getStringExtra("part");

        flowerTitle.setText(name);
        flowerIntroduce.setText(introduce);
        flowerValue.setText(value);
        flowerPart.setText(part);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}