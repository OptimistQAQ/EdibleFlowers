package com.example.edibleflowers.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edibleflowers.R;

/**
 * @author 65667
 */
public class DetailActivity extends AppCompatActivity {

    private ImageView detailImg;
    private TextView detailTitle, detailMain, detailAuthor;

    private String img, title, main, author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = getIntent().getStringExtra("img");
        title = getIntent().getStringExtra("title");
        main = getIntent().getStringExtra("main");
        author = getIntent().getStringExtra("author");

        initView();
    }

    private void initView() {
        detailImg = findViewById(R.id.detail_img);
        detailAuthor = findViewById(R.id.detail_author);
        detailMain = findViewById(R.id.detail_main);
        detailTitle = findViewById(R.id.detail_title);

        Glide.with(DetailActivity.this).load(img).into(detailImg);
        detailTitle.setText(title);
        detailAuthor.setText(author);
        detailMain.setText("\u3000\u3000" + main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}