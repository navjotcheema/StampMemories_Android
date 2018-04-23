package com.stampmemories.core.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stampmemories.R;
import com.stampmemories.core.model.CountryData;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by AppsterBiz on 26-Dec-17
 */

public class CountryCodeAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<CountryData> list = new ArrayList<>();

    public CountryCodeAdapter(Activity context, ArrayList<CountryData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_country_code_selection_item, parent, false);
        TextView code = convertView.findViewById(R.id.list_item_code);
        TextView name = convertView.findViewById(R.id.list_item_name);
        ImageView flag = convertView.findViewById(R.id.list_item_flag);
        CountryData countrydata = list.get(position);
        code.setText(countrydata.getDial_code());
        name.setText(countrydata.getName());
        int rid = context.getResources().getIdentifier(countrydata.getCode().toLowerCase(), "raw", context.getPackageName());
        if (rid != 0) {
            Resources res = context.getResources();
            InputStream in = res.openRawResource(rid);
            Drawable image = Drawable.createFromStream(in, countrydata.getCode().toLowerCase());
            flag.setImageDrawable(image);
        }
        return convertView;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_dial_code, viewGroup, false);

        TextView code = view.findViewById(R.id.itemText);
        CountryData CountryData = list.get(i);
        code.setText(CountryData.getDial_code());
        return view;
    }
}
