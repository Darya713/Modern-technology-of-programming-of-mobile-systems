package com.butterfly.lab_10_11.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.butterfly.lab_10_11.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Item> list;

    ListAdapter(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = view;
        if (view == null)
            view = inflater.inflate(R.layout.activity_item, viewGroup, false);

        Item item = getMyItem(i);
        ((TextView) view.findViewById(R.id.item_title)).setText(item.getTitle());
        ImageView image = (ImageView) view.findViewById(R.id.item_image);
        image.setImageResource(R.drawable.star);
        if (item.isStar() == 1) {
            image.setVisibility(View.VISIBLE);
        }
        else {
            image.setVisibility(View.GONE);
        }
        return view;
    }

    private Item getMyItem(int position) {
        return (Item) getItem(position);
    }
}
