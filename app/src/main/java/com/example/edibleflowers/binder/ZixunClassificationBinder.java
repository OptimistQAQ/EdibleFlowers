package com.example.edibleflowers.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edibleflowers.R;
import com.example.edibleflowers.item.ZixunClassificationItem;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 65667
 */
public class ZixunClassificationBinder extends ItemViewBinder<ZixunClassificationItem, ZixunClassificationBinder.ViewHolder> {

    private ZixunClassificationItem zixunClassificationItem;

    private View root;
    private ImageView img;
    private TextView name;
    private TextView huayu;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        root = inflater.inflate(R.layout.item_classification, parent, false);
        initView();
        return new ZixunClassificationBinder.ViewHolder(root);
    }

    private void initView() {
        img = root.findViewById(R.id.classification_img);
        name = root.findViewById(R.id.classification_name);
        huayu = root.findViewById(R.id.classification_huayu);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ZixunClassificationItem item) {
        holder.setIsRecyclable(false);
        this.zixunClassificationItem = item;
        Glide.with(root.getContext()).load(item.getImg()).into(img);
        name.setText(item.getName());
        huayu.setText(item.getHuayu());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
