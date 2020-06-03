package com.abhinav.covid19tracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_all_report extends BaseAdapter {

    private Context context;
    private ArrayList<AllReportInfo>list=new ArrayList<>();
    List<AllReportInfo>tempList=null;

    public Adapter_all_report(List<AllReportInfo>lst,Context cont)
    {
        this.tempList=lst;
        this.context=cont;
        this.list.addAll(lst);
    }
    @Override
    public int getCount() {
        return this.tempList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.tempList.get(i);
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null)
        {
            LayoutInflater inf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inf.inflate(R.layout.layout_item_all_report,null);
            holder=new ViewHolder();
            convertView.setTag(holder);
        }else
        {
            holder=(ViewHolder) convertView.getTag();
        }
        holder.countryName=convertView.findViewById(R.id.countryName);
        holder.Case=convertView.findViewById(R.id.Case);
        holder.Death=convertView.findViewById(R.id.Death);
        holder.Recovered=convertView.findViewById(R.id.Recovered);
        holder.ly=convertView.findViewById(R.id.ly);

        AllReportInfo info=tempList.get(position);
        holder.countryName.setText(info.getCountry());
        holder.Case.setText(info.getCase());
        holder.Death.setText(info.getDeath());
        holder.Recovered.setText(info.getRecovered());
        return convertView;
    }
    public static class ViewHolder{
        public TextView countryName,Case,Death,Recovered;
        public LinearLayout ly;
    }
}