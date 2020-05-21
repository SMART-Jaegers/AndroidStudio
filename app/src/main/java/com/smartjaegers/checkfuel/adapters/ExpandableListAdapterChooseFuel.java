package com.smartjaegers.checkfuel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapterChooseFuel extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeaders;
    private HashMap<String, List<String>> listItem;

    public ExpandableListAdapterChooseFuel(Context context, List<String> listDataHeaders, HashMap<String, List<String>> listItem) {
        this.context = context;
        this.listDataHeaders = listDataHeaders;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        return listDataHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItem.get(listDataHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItem.get(listDataHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_for_choose_filters, null);
        }
        TextView headerView = convertView.findViewById(R.id.choose_sort_list_header);
        headerView.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        View grid = convertView;
        ChildViewHolder childViewHolder = new ChildViewHolder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_for_choose_filters, null);

            childViewHolder.mChildText = convertView.findViewById(R.id.checkedTextView);
            convertView.setTag(R.layout.list_item_for_choose_filters, childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag(R.layout.list_item_for_choose_filters);
        }

        childViewHolder.mChildText.setText(childText);

        childViewHolder.mChildText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setBackgroundColor(context.getResources().getColor(R.color.colorPurpleWhite));
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public final class ChildViewHolder {

        TextView mChildText;
        CheckBox mCheckBox;
    }
}
