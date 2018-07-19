package com.example.youngwookwon.myproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.youngwookwon.myproject.Info.InfoActivity;

import java.util.List;

/**
 * Created by user on 2018-07-07.
 */

public class Home_View_Adapter extends RecyclerView.Adapter<Home_View_Adapter.ViewHolder>{
    Context context;
    List<Home_View_Item> items;
    int item_layout;
    public Home_View_Adapter(Context context, List<Home_View_Item> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Home_View_Item item=items.get(position);
        String adr = item.getImage();
        Glide.with(context).load(adr).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher).centerCrop()).into(holder.image);
        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate());
        holder.place.setText(item.getPlace());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("cultcode", item.getCode());
                context.startActivity(intent);
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView place;
        TextView date;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            place = (TextView)itemView.findViewById(R.id.place);
            date = (TextView)itemView.findViewById(R.id.date);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}

