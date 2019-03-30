package com.lab_work;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<String>  {

    private final static int SIZE_OF_LIST = 1000000;
    public  static String[] nums = new String[SIZE_OF_LIST];
    private static ArrayList<ItemModel> items = new ArrayList<>();

    private static class ViewHolder {
        ImageView imageView;
        TextView numberView;
    }

    public ListAdapter(Context context) {
        super(context, R.layout.list_item, nums);
        ItemModel item = new ItemModel();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.numberView = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemModel item = new ItemModel();

        viewHolder.imageView.setImageResource(getContext().getResources().getIdentifier("pic_" + String.valueOf(position % 100),
                "drawable", getContext().getPackageName()));
        viewHolder.numberView.setText(item.num2words(position + 1,1));

        if(position %2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#CCCCCC"));
        }
        else {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        return convertView;
    }
}