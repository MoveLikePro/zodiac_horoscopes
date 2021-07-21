package com.enigmaticdevs.zodiachoroscopes.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enigmaticdevs.zodiachoroscopes.ItemAdapters.DailyItemAdapter;
import com.enigmaticdevs.zodiachoroscopes.ItemAdapters.MonthlyItemAdapter;
import com.enigmaticdevs.zodiachoroscopes.ItemAdapters.WeeklyItemAdapter;
import com.enigmaticdevs.zodiachoroscopes.R;

import java.util.ArrayList;
import java.util.List;

import bot.box.horology.annotation.SUNSIGN;

public class FragmentMonthly extends Fragment {
    RecyclerView recyclerView;
    List<String> Date;
    List<String> ZodiacSign;
    List<Integer> ZodiacImg;
    List<String> ZodiacCall;
    MonthlyItemAdapter itemAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_monthly, container, false);
        addZodiacImg();
        addZodiacSign();
        addZodiacCall();
        addDates();
        recyclerView = v.findViewById(R.id.monthly_recyclerview);
        itemAdapter = new MonthlyItemAdapter(ZodiacSign,ZodiacImg,Date,ZodiacCall,getContext());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
          recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        return v;
    }
    private void addZodiacCall() {
        ZodiacCall = new ArrayList<>();
        ZodiacCall.add(SUNSIGN.AQUARIUS);
        ZodiacCall.add(SUNSIGN.PISCES);
        ZodiacCall.add(SUNSIGN.ARIES);
        ZodiacCall.add(SUNSIGN.TAURUS);
        ZodiacCall.add(SUNSIGN.GEMINI);
        ZodiacCall.add(SUNSIGN.CANCER);
        ZodiacCall.add(SUNSIGN.LEO);
        ZodiacCall.add(SUNSIGN.VIRGO);
        ZodiacCall.add(SUNSIGN.LIBRA);
        ZodiacCall.add(SUNSIGN.SCORPIO);
        ZodiacCall.add(SUNSIGN.SAGITTARIUS);
        ZodiacCall.add(SUNSIGN.CAPRICORN);
    }

    private void addDates() {
        Date = new ArrayList<>();
        Date.add("Jan 20-Feb 18");
        Date.add("Feb 19-Mar 20");
        Date.add("Mar 21-Apr 18");
        Date.add("Apr 20-May 20");
        Date.add("May 21-Jun 20");
        Date.add("Jun 21-Jul 22");
        Date.add("Jul 23-Aug 22");
        Date.add("Aug 23-Sep 22");
        Date.add("Sep 23-Oct 22");
        Date.add("Oct 23-Nov 21");
        Date.add("Nov 22-Dec 21");
        Date.add("Dec 22-Jan 19");

    }

    public void addZodiacSign(){
        ZodiacSign=new ArrayList<>();
        ZodiacSign.add("Aquarius");
        ZodiacSign.add("Pisces");
        ZodiacSign.add("Aries");
        ZodiacSign.add("Taurus");
        ZodiacSign.add("Gemini");
        ZodiacSign.add("Cancer");
        ZodiacSign.add("Leo");
        ZodiacSign.add("Virgo");
        ZodiacSign.add("Libra");
        ZodiacSign.add("Scorpio");
        ZodiacSign.add("Sagittarius");
        ZodiacSign.add("Capricorn");
    }

    public void addZodiacImg(){
        ZodiacImg= new ArrayList<>();
        ZodiacImg.add(R.drawable.aquarius);
        ZodiacImg.add(R.drawable.pisces);
        ZodiacImg.add(R.drawable.aries);
        ZodiacImg.add(R.drawable.taurus);
        ZodiacImg.add(R.drawable.gemini);
        ZodiacImg.add(R.drawable.cancer);
        ZodiacImg.add(R.drawable.leo);
        ZodiacImg.add(R.drawable.virgo);
        ZodiacImg.add(R.drawable.libra);
        ZodiacImg.add(R.drawable.scorpio);
        ZodiacImg.add(R.drawable.sagittarius);
        ZodiacImg.add(R.drawable.capricorn);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
