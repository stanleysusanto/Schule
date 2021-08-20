package com.recipes.dewordy.fragment.CategoryFragment.TabFragment;

/**
 * Created by Paulstanley on 3/5/16.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recipes.dewordy.MainActivity3;
import com.recipes.dewordy.R;
import com.recipes.dewordy.SharedPreference;
import com.recipes.dewordy.fragment.CategoryFragment.FifthFragment;
import com.recipes.dewordy.helper.Conf;
import com.recipes.dewordy.model.Json5;
import com.recipes.dewordy.model.category.Category;
import com.recipes.dewordy.model.chat.ChatReq;
import com.recipes.dewordy.model.chat.ChatRsp;
import com.recipes.dewordy.model.chat.CommentRsp;
import com.recipes.dewordy.rest.RestClient;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TabFragment1 extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listview;

    ListView listcomment;
    List<Category> categories;
    ListAdapter la;
    static String[] RecipeName;
    static String[] Preview;
    static String[] CookTime;

    List<String> chats = new ArrayList<>();
//    static String[] RecipeName;
//    static String[] Preview;
//    static String[] CookTime;

    private static final int PICK_IMAGE = 100;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        ImageView refreshicon = (ImageView) view.findViewById(R.id.refreshbutton);

        refreshicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshContent();
                RestClient.get().getChat(FifthFragment.id2, new Callback<Json5>() {
                    @Override
                    public void success(Json5 json5, Response response) {
                        List<ChatRsp> chatRsps = json5.getChat();
                        ListAdapter listAdapter = new ListAdapter(MainActivity3.getInstance().getApplicationContext(), chatRsps);
                        listview.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);

                refreshContent();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });



        final EditText editchat = (EditText) view.findViewById(R.id.chatbar);
        editchat.setSelectAllOnFocus(true);
        editchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editchat.setCursorVisible(false);
            }
        });
        Button button1 = (Button) view.findViewById(R.id.button1);
        ImageView button2 = (ImageView) view.findViewById(R.id.button2);
        listview = (ListView) view.findViewById(R.id.listView);
//        listcomment = (ListView) view.findViewById(R.id.listcomment);
//        la = new ListAdapter(view.getContext());


//        if (LoginState.getUsername() == null) {
        RestClient.get().getChat(FifthFragment.id2, new Callback<Json5>() {
            @Override
            public void success(Json5 json5, Response response) {
                List<ChatRsp> chatRsps = json5.getChat();
//
//                for(ChatRsp chat : chatRsps) {
//                    chats.add(chat.getMessage());
//                }
//
//                String[] chatArr = new String[chats.size()];
//                chatArr = chats.toArray(chatArr);
//
//                ArrayAdapter<String> chatAdapter = new ArrayAdapter<String>
//                        (view.getContext(), android.R.layout.simple_list_item_1, chatArr);
                ListAdapter listAdapter = new ListAdapter(MainActivity3.getInstance().getApplicationContext(), chatRsps);
                listview.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

//        editchat.setText("");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chats.clear();

                ChatReq chat = new ChatReq();
                chat.setId(FifthFragment.id2);
                chat.setMessage(editchat.getText().toString());
//                chat.setImageString(LoginState.getImage());

                if(SharedPreference.getusername(MainActivity3.getInstance().getApplicationContext()).length() == 0) {
                    chat.setName(FifthFragment.nama);
                    chat.setImageString("");

                }else {
                    chat.setName(SharedPreference.getusername(MainActivity3.getInstance().getApplicationContext()));
//                    chat.setImageString(SharedPreference.getimage(MainActivity3.getInstance().getApplicationContext()));
                    chat.setImageString("");
                }

                RestClient.get().getDetail2(chat, new Callback<Json5>() {
                    @Override
                    public void success(Json5 json5, Response response) {
                        List<ChatRsp> chatRsps = json5.getChat();
                        ListAdapter listAdapter = new ListAdapter(MainActivity3.getInstance().getApplicationContext(), chatRsps);
                        listview.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                editchat.setText("");
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
        return view;
    }

    /** This class is used to create custom listview */

    static class ListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Context ctx;
        private List<ChatRsp> chatRsps;
        private List<CommentRsp> commentRsps;
        String[] comments = new String[] {"1","2","3","4"};

        public ListAdapter(Context context, List<ChatRsp> chatRsps) {
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
//                holder.like = (TextView) convertView.findViewById(R.id.like);
//                holder.totallike = (TextView) convertView.findViewById(R.id.totallike);
//                    holder.comment = (TextView) convertView.findViewById(R.id.comment);
                holder.imgPreview = (ImageView) convertView.findViewById(R.id.imgPreview);
                holder.rowpic = (ImageView) convertView.findViewById(R.id.rowpic);
                holder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);
//                holder.listComment = (ListView) convertView.findViewById(R.id.listcomment);


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



//            if(LoginState.getUsername() == null) {
//                holder.txtRecipeName.setText(chatRsps.get(position).getUsername());
//            }else{
//                holder.txtRecipeName.setText(chatRsps.get(position).getUsername() + " - " + chatRsps.get(position).getNamasekolah());
//            }
//            if(chatRsps.get(position).getNamasekolah().equals("")){
                holder.txtRecipeName.setText(chatRsps.get(position).getUsername());
//            }else {
//                holder.txtRecipeName.setText(chatRsps.get(position).getUsername() + " - " + chatRsps.get(position).getNamasekolah());
//            }
            holder.txtReadyIn.setText(chatRsps.get(position).getLiness());
//            holder.totallike.setText(chatRsps.get(position).getLike() + " likes");
            holder.timestamp.setText(chatRsps.get(position).getcreated_post());
            Picasso.with(MainActivity3.getInstance()).load(Conf.imageurl2 + chatRsps.get(position).getUsername() + ".png").into(holder.imgPreview);

//            holder.listComment.setAdapter(new ArrayAdapter<String>(MainActivity36.getInstance(), android.R.layout.simple_list_item_1, comments));
//            if (image.equals("")) {
                holder.rowpic.setVisibility(View.VISIBLE);
            Picasso.with(MainActivity3.getInstance()).load(Conf.imageurl3 + image + ".png").into(holder.rowpic);
//            } else {
//                holder.rowpic.setVisibility(View.GONE);
//            }

//            holder.like.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Like like = new Like();
//                    like.setLine_id(chatRsps.get(position).getLine_id());
//                    like.setUserid(SharedPreference.getuserid(MainActivity3.getInstance().getApplicationContext()));
//                    RestClient.get().getLike(like, new Callback<Likejson>() {
//                        @Override
//                        public void success(Likejson likejson, Response response) {
//                            holder.totallike.setText(likejson.getTotal().toString() + "likes");
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            Log.d("s",error.getMessage());
//                        }
//                    });
//                }
//            });

//                holder.comment.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        final Dialog dialog = new Dialog(MainActivity36.getInstance());
//                        dialog.setContentView(R.layout.name_box);
//                        dialog.setTitle("Comment");
//
//                        final EditText editText = (EditText) dialog.findViewById(R.id.editnama);
//                        Button button = (Button) dialog.findViewById(R.id.buttonnama);
//
//                        Comment2Rsp comment2Rsp = new Comment2Rsp();
////                        comment2Rsp.setComment();
//
//                        dialog.show();
//                    }
//                });

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
            TextView txtRecipeName, txtRecipeNamec, txtReadyIn, txtReadyInc, like, comment, totallike, timestamp;
            ImageView imgPreview, imgPreviewc, rowpic;
            ListView listComment;
        }

    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == MainActivity3.getInstance().RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(MainActivity3.getInstance().getContentResolver(), filePath);
                long free = Runtime.getRuntime().freeMemory();
                ChatReq chat = new ChatReq();
                chat.setId(FifthFragment.id2);
                chat.setImageString(getStringImage(bitmap));
//                if(SharedPreference.getusername(MainActivity3.getInstance().getApplicationContext()).length() == 0) {
                    chat.setName(FifthFragment.nama);
//                }else{
//                    chat.setName(SharedPreference.getusername(MainActivity3.getInstance().getApplicationContext()));
//                }
                RestClient.get().doPostPhoto(chat, new Callback<Json5>() {
                    @Override
                    public void success(Json5 json5, Response response) {
                        List<ChatRsp> chatRsps = json5.getChat();
                        ListAdapter listAdapter = new ListAdapter(MainActivity3.getInstance().getApplicationContext(), chatRsps);
//                        listview.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static int calculateInSampleSize(BitmapFactory.Options ourOption,
                                            int imageWidth, int imageHeight) {
        final int height = ourOption.outHeight;
        final int width = ourOption.outWidth;
        int inSampleSize = 1;
        if (height > imageHeight || width > imageWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) imageHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) imageWidth);
            }
        }
        return inSampleSize;
    }

    private void refreshContent() {
        RestClient.get().getChat(FifthFragment.id2, new Callback<Json5>() {
            @Override
            public void success(Json5 json5, Response response) {
                List<ChatRsp> chatRsps = json5.getChat();
//
//                for(ChatRsp chat : chatRsps) {
//                    chats.add(chat.getMessage());
//                }
//
//                String[] chatArr = new String[chats.size()];
//                chatArr = chats.toArray(chatArr);
//
//                ArrayAdapter<String> chatAdapter = new ArrayAdapter<String>
//                        (view.getContext(), android.R.layout.simple_list_item_1, chatArr);
                ListAdapter listAdapter = new ListAdapter(MainActivity3.getInstance().getApplicationContext(), chatRsps);
                listview.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        mSwipeRefreshLayout.setRefreshing(false);
    }
}