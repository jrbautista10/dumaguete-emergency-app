package com.example.dumagueteemergency;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class EmergencyAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String, List<String>> Emergency_Category;
    private List<String> Emergency_List;

    public EmergencyAdapter(Context ctx, HashMap<String, List<String>> Emergency_Category, List<String> Emergency_List) {
        this.ctx = ctx;
        this.Emergency_Category = Emergency_Category;
        this.Emergency_List = Emergency_List;
    }

    @Override
    public int getGroupCount() {
        return Emergency_List.size();
    }

    @Override
    public int getChildrenCount(int arg0) {
        return Emergency_Category.get(Emergency_List.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0) {
        return Emergency_List.get(arg0);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Emergency_Category.get(Emergency_List.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title = (String) getGroup(parent);
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.parent_layout, parentView, false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentView) {
        String child_title = (String) getChild(parent, child);
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.child_layout, parentView, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView child_textview = (TextView) convertView.findViewById(R.id.child_txt);

        if (child_title.contains("Facebook")) {
            img.setImageResource(R.drawable.facebook);
        } else if (child_title.contains("Messenger")) {
            img.setImageResource(R.drawable.messenger);
        } else {
            img.setImageResource(R.drawable.phone);
        }

        child_textview.setText(child_title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
