package com.recipes.dewordy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recipes.dewordy.MainActivity;
import com.recipes.dewordy.R;
import com.recipes.dewordy.model.chat.ChatRsp;
import com.recipes.dewordy.model.chat.CommentRsp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Paulstanley on 3/29/16.
 */
public class CommentAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context ctx;
    private List<ChatRsp> chatRsps;
    private List<CommentRsp> commentRsps;
    String[] comments = new String[] {"1","2","3","4"};

    public CommentAdapter(Context context, List<ChatRsp> chatRsps) {
        inflater = LayoutInflater.from(context);
        ctx = context;
        this.chatRsps = chatRsps;
        this.commentRsps = commentRsps;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return chatRsps.size();
//                return commentRsps.size();
    }

    public int getCount2() {
        // TODO Auto-generated method stub
//                return chatRsps.size();
        return commentRsps.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row2, null);
            holder = new ViewHolder();
            holder.txtRecipeName = (TextView) convertView.findViewById(R.id.txtRecipeName);
            holder.txtReadyIn = (TextView) convertView.findViewById(R.id.txtReadyIn);
//            holder.like = (TextView) convertView.findViewById(R.id.like);
//            holder.totallike = (TextView) convertView.findViewById(R.id.totallike);
//            holder.comment = (TextView) convertView.findViewById(R.id.comment);
            holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
            holder.rowpic = (ImageView) convertView.findViewById(R.id.rowpic);
            holder.listComment = (ListView) convertView.findViewById(R.id.listcomment);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//            public View getView(final int position, View convertView, ViewGroup parent) {
//                // TODO Auto-generated method stub
//                final ViewHolder holder;
//
//                if (convertView == null) {

//                    convertView2 = inflater.inflate(R.layout.row_comment, null);
//                    holder.imgPreviewc = (ImageView) convertView.findViewById(R.id.imgPreviewc);
//                    holder.txtReadyInc = (TextView) convertView.findViewById(R.id.txtReadyInc);
//                    holder.txtRecipeNamec = (TextView) convertView.findViewById(R.id.txtRecipeNamec);
//
//                }
        String image = chatRsps.get(position).getImages();
        holder.txtRecipeName.setText(chatRsps.get(position).getUsername());
        holder.txtReadyIn.setText(chatRsps.get(position).getLiness());

        holder.listComment.setAdapter(new ArrayAdapter<String>(MainActivity.getInstance(), android.R.layout.simple_list_item_1, comments));
        if (!image.equals("")) {
            holder.rowpic.setVisibility(View.VISIBLE);
            Picasso.with(ctx).load(image).into(holder.rowpic);
        }

//
//  else {
//                //tampilin image
//            }

//            holder.txtRecipeName.setText(chatRsps.get(position).getUsername());
//            holder.txtReadyIn.setText(chatRsps.get(position).getLiness());
//            int imagePreview = ctx.getResources().getIdentifier(chatRsps.get(position).getImages(), "drawable", ctx.getPackageName());
//            holder.imgPreview.setImageResource(imagePreview);


        return convertView;
    }
    static class ViewHolder {
        TextView txtRecipeName, txtRecipeNamec, txtReadyIn, txtReadyInc, like, comment, totallike;
        ImageView imgPreview, imgPreviewc, rowpic;
        ListView listComment;
    }

}
