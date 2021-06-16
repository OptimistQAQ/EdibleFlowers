package com.example.edibleflowers.binder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edibleflowers.R;
import com.example.edibleflowers.activity.FlowerActivity;
import com.example.edibleflowers.item.ZixunHighqualityItem;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 65667
 */
public class ZixunHighqualityBinder extends ItemViewBinder<ZixunHighqualityItem, ZixunHighqualityBinder.ViewHolder> {

    private ZixunHighqualityItem zixunHighqualityItem;
    private View root;
    private TextView name;


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        root = inflater.inflate(R.layout.item_high_quality, parent, false);
        initView();
        return new ViewHolder(root);
    }

    private void initView() {
        name = root.findViewById(R.id.high_name);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ZixunHighqualityItem item) {
        holder.setIsRecyclable(false);
        this.zixunHighqualityItem = item;
        name.setText(item.getName());
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), FlowerActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("introduce", item.getIntroduce());
                intent.putExtra("value", item.getValue());
                intent.putExtra("part", item.getPart());
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
