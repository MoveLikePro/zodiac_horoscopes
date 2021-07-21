package com.enigmaticdevs.zodiachoroscopes.ItemAdapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.enigmaticdevs.zodiachoroscopes.MainActivity;
import com.enigmaticdevs.zodiachoroscopes.R;

import java.util.List;

public class NavItemAdapter extends RecyclerView.Adapter<NavItemAdapter.ViewHolder> {
    List<String> menu;
    List<Integer> icons;
    Context context;

    public NavItemAdapter(List<String> menu, List<Integer> icons, Context context) {
        this.menu = menu;
        this.icons = icons;
        this.context = context;
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView menu_name;
        ImageView menu_image;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_name = itemView.findViewById(R.id.menu_item_name);
            menu_image= itemView.findViewById(R.id.menu_item_image);
            constraintLayout = itemView.findViewById(R.id.item_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.menu_list_item,parent,false);
        final ViewHolder vh = new ViewHolder(v);
        vh.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(menu.get(vh.getAdapterPosition())){
                    case "Rate App":
                    {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.enigmaticdevs.zodiachoroscopes")));
                        break;
                    }
                    case "Share":
                    {
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Take a look at this awesome Zodiac Horoscopes app I found on play store https://play.google.com/store/apps/details?id=com.enigmaticdevs.zodiachoroscopes ";
                        String shareSub = "Android Code Snippets";
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
                        break;
                    }
                    case "Checkout My Other Apps":
                    {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7260563907283082601&hl=en")));
                        break;
                    }
                    }
                    }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.menu_name.setText(menu.get(position));
       holder.menu_image.setImageResource(icons.get(position));
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }


}
