package com.enigmaticdevs.zodiachoroscopes.ItemAdapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.enigmaticdevs.zodiachoroscopes.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import bot.box.horology.annotation.DURATION;
import bot.box.horology.api.Horoscope;
import bot.box.horology.delegate.Response;
import bot.box.horology.hanshake.HorologyController;
import bot.box.horology.pojo.Zodiac;

public class MonthlyItemAdapter extends RecyclerView.Adapter<MonthlyItemAdapter.ViewHolder> {
    private List<Integer> ZodiacImg;
    private List<String> ZodiacSign;
    private List<String> Date;
    private List<String> zodiacCall;
    private Context mContext;
    private ProgressDialog dialog;

    public MonthlyItemAdapter(List<String> zodiacSign, List<Integer> zodiacImg, List<String> date, List<String> zodiacCall, Context context) {
        this.ZodiacSign = zodiacSign;
        this.ZodiacImg = zodiacImg;
        this.Date = date;
        this.zodiacCall = zodiacCall;
        this.mContext = context;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView textView;
        private TextView birthday;
        private FrameLayout frameLayout;
        private ImageView img;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            frameLayout = itemView.findViewById(R.id.frame_layout);
            birthday= itemView.findViewById(R.id.birthday);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_values, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        vh.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(mContext,R.style.MyProgressDialog);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setTitle("Loading...");
                dialog.show();
                String Url = zodiacCall.get(vh.getAdapterPosition());
                Log.d("urls", Url);
                Horoscopeget(Url,vh.getAdapterPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String al = ZodiacSign.get(position);
        holder.textView.setText(al.toUpperCase());
        Log.d("damn", String.valueOf(position));
        final String bl= Date.get(position);
        holder.birthday.setText(bl);
        holder.img.setImageResource(ZodiacImg.get(position));
    }
    private void Horoscopeget(String Url, final int id) {
        final Horoscope haquarius = new Horoscope.Zodiac(mContext)
                .requestSunSign(Url)
                .requestDuration(DURATION.MONTH)
                .isDebuggable(true)
                .fetchHoroscope();
        final HorologyController caquarius = new HorologyController(new Response() {
            @Override
            public void onResponseObtained(Zodiac zodiac) {
                dialog.dismiss();
                String horoscope = zodiac.getHoroscope();
                horoscope = horoscope.replace("says Ganesha", "your Zodiac");
                horoscope = horoscope.replace("Ganesha", "your Zodiac");
                horoscope = horoscope.replace("[","");
                horoscope = horoscope.replace("]","");
                new MaterialAlertDialogBuilder(mContext)
                        .setMessage(horoscope)
                        .setIcon(ZodiacImg.get(id))
                        .setTitle(ZodiacSign.get(id))
                        .setPositiveButton("Ok", null)
                        .show();
            }
            @Override
            public void onErrorObtained(String errormsg) {
                Toast.makeText(mContext, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
        caquarius.requestConstellations(haquarius);
    }
    @Override
    public int getItemCount() {
        return ZodiacSign.size();
    }


}
