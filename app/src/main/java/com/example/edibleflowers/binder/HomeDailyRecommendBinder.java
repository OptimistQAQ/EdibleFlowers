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
import com.example.edibleflowers.item.HomeDailyRecommendItem;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 65667
 */
public class HomeDailyRecommendBinder extends ItemViewBinder<HomeDailyRecommendItem, HomeDailyRecommendBinder.ViewHolder> {

    private View root;
    private HomeDailyRecommendItem homeDailyRecommendItem;
    
    private ImageView recommendImg;
    private TextView recommendTitle, recommendAuthor;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        root = inflater.inflate(R.layout.item_recommend, parent, false);
        initView();
        return new ViewHolder(root);
    }

    private void initView() {
        recommendImg = root.findViewById(R.id.recommend_img);
        recommendTitle = root.findViewById(R.id.recommend_title);
        recommendAuthor = root.findViewById(R.id.recommend_author);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HomeDailyRecommendItem item) {
        holder.setIsRecyclable(false);
        this.homeDailyRecommendItem = item;
        Glide.with(root.getContext()).load(item.getImg()).into(recommendImg);
        recommendAuthor.setText(item.getAuthor());
        recommendTitle.setText(item.getTitle());

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), DetailActivity.class);
                intent.putExtra("img", item.getImg());
                intent.putExtra("title", item.getTitle());
                intent.putExtra("author", item.getAuthor());
                intent.putExtra("main", item.getMain());
                root.getContext().startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
