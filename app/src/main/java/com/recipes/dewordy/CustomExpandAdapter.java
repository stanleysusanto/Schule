package com.recipes.dewordy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandAdapter extends BaseExpandableListAdapter {

    private List<SampleTO> parentRecord;
    private HashMap<String, List<String>> childRecord;
    private LayoutInflater inflater = null;
    private Activity mContext;

    public CustomExpandAdapter(Activity context, List<SampleTO> parentList, HashMap<String, List<String>> childList) {
        this.parentRecord = parentList;
        this.childRecord = childList;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return this.childRecord.get(((SampleTO) getGroup(groupPosition)).getTitle()).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String childConfig = getChild(groupPosition, childPosition);

        final ViewHolder holder;
        try {
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = inflater.inflate(R.layout.custom_list_view_child, null);
                holder.childTitle = (TextView) convertView.findViewById(R.id.childTitle);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.childTitle.setText(childConfig);

        } catch (Exception e) {
        }
        return convertView;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {

        SampleTO parentSampleTo = parentRecord.get(groupPosition);

        final ViewHolder holder;
        try {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.custom_list_view, null);
                holder = new ViewHolder();

                holder.parentTitle = (TextView) convertView.findViewById(R.id.parentTitle);
                holder.parentIcon = (ImageView) convertView.findViewById(R.id.parentIcon);
                holder.plusminus = (ImageView) convertView.findViewById(R.id.plusminus);
                final View finalConvertView = convertView;
                holder.plusminus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Toast.makeText(finalConvertView.getContext(), "fdfdfdfds", Toast.LENGTH_SHORT).show();
                            ExpandableListView mExpandableListView = (ExpandableListView) parent;

                            if(((ExpandableListView) parent).isGroupExpanded(groupPosition)) {
                                mExpandableListView.collapseGroup(groupPosition);
                                holder.plusminus.setImageResource(R.drawable.iconplus);
                            }else {
                                mExpandableListView.expandGroup(groupPosition);
                                holder.plusminus.setImageResource(R.drawable.iconminus);
                            }
                        }
                    });
                if(groupPosition == 0){
                    holder.plusminus.setVisibility(View.GONE);
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.parentTitle.setText(parentSampleTo.getTitle());
            holder.parentIcon.setBackgroundResource(parentSampleTo.getIcon());

        } catch (Exception e) {
        }
        return convertView;
    }

    public static class ViewHolder {

        private TextView childTitle;
        // Content
        private TextView parentTitle;
        private ImageView parentIcon, plusminus;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupPosition == 0) {
            return 0;
        } else {
            return this.childRecord.get(((SampleTO) getGroup(groupPosition)).getTitle()).size();
//            return 0;
//            this.notifyDataSetChanged();
//            Object task = this.getChild(groupPosition, childPosition);
//            _listDataChild.remove(task);
//            this.notifyDataSetChanged();
//            return childRecord.get(parentRecord.get(groupPosition)).size();
//            return ((ArrayList<String>) childRecord.get(groupPosition)).size();
//            return 0;
        }
    }

    @Override
    public SampleTO getGroup(int groupPosition) {
        return this.parentRecord.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parentRecord.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
