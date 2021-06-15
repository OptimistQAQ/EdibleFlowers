package com.example.edibleflowers.binder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edibleflowers.R;
import com.example.edibleflowers.activity.DetailActivity;
import com.example.edibleflowers.item.ZixunNewsItem;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 65667
 */
public class ZixunNewsBinder extends ItemViewBinder<ZixunNewsItem, ZixunNewsBinder.ViewHolder> {

    private ZixunNewsItem zixunNewsItem;

    private View root;
    private ImageView imgNews;
    private TextView newsTitle, newsAuthor, newsTime;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        root = inflater.inflate(R.layout.item_news, parent, false);
        initView();
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ZixunNewsItem item) {
        holder.setIsRecyclable(false);
        this.zixunNewsItem = item;
        Glide.with(root.getContext()).load(item.getImg_news()).into(imgNews);
        newsTitle.setText(item.getTitle());
        newsAuthor.setText(item.getAuthor());
        newsTime.setText(item.getTime());

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), DetailActivity.class);
                intent.putExtra("img", item.getImg_news());
                intent.putExtra("title", item.getTitle());
                intent.putExtra("author", item.getAuthor());
                intent.putExtra("main", item.getMain());
                root.getContext().startActivity(intent);
            }
        });
    }

    private void initView() {
        imgNews = root.findViewById(R.id.img_news);
        newsTitle = root.findViewById(R.id.item_news_title);
        newsAuthor = root.findViewById(R.id.item_news_source);
        newsTime = root.findViewById(R.id.item_news_date);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
