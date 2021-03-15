package com.example.apiclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiclient.adapter.ItemListener;
import com.example.apiclient.api.UserData;
import com.example.apiclient.ui.ImageUtils;
import com.example.apiclient.ui.LogoView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<UserData> items;

//    UserAdapter(ArrayList<UserData> items) {
//        this.items = items;
//    }

    private LayoutInflater mInflater;
    Context context;
    ItemListener itemListener;

    public UserAdapter(Context context, ArrayList<UserData> mImgarraylist , ItemListener itemListener){
        this.context = context;
        this.items = mImgarraylist;
        this.itemListener = itemListener;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.user_item, parent, false));
    }
//LinkedTreeMap
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mHolder, final int position) {
        MyViewHolder holder = (MyViewHolder)mHolder;
        UserData userData = items.get(position);
        holder.mImageView.setImage(userData.getAvatar_url(), LogoView.CIRCLEIMAGE);

        holder.mUserName.setText(userData.getLogin());
        if (userData.isSite_admin()) {
            holder.mUserStaff.setVisibility(View.VISIBLE);
        } else {
            holder.mUserStaff.setVisibility(View.GONE);
        }
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.ItemListener(position);
            }
        });
    }



//    public static UserData map2Object_3(Map<String, Object> map, Class<?> clazz) {
//        // Map<String, User> 轉 json
//        HashMap<String, UserData> umap = new HashMap<>();
//        UserData user3 = new UserData(123, "John");
//        umap.put("John", user3);
//        String mjson2 = objectMapper.writeValueAsString(umap);
//        return user3;
//    }
//
//    public static Object mapToObject(Map<String, Object> map, Class<?> cla) throws Exception {
//        if (map == null)
//            return null;
//        Object obj = cla.newInstance();
//        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
//        return obj;
//    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName;
        LogoView mImageView;
        LinearLayout mLinearLayout;
        TextView mUserStaff;

        public MyViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.user_layout);
            mImageView = itemView.findViewById(R.id.user_image);
            mUserName = itemView.findViewById(R.id.user_name);
            mUserStaff = itemView.findViewById(R.id.user_staff);

        }
    }
}
