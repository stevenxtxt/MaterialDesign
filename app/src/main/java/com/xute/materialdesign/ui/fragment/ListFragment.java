package com.xute.materialdesign.ui.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xute.materialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xute on 2016/11/24.
 */

public class ListFragment extends Fragment {
    @BindView(R.id.rv_list) RecyclerView rvList;

    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        ButterKnife.bind(this, view);

        adapter = new ListAdapter(getActivity());
        rvList.setAdapter(adapter);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
        private static final int LENGTH = 18;
        private String[] names;
        private String[] des;
        private Drawable[] avatars;

        public ListAdapter(Context context) {
            Resources resources = context.getResources();
            names = resources.getStringArray(R.array.places);
            des = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            avatars = new Drawable[a.length()];
            for (int i = 0; i < a.length(); i++) {
                avatars[i] = a.getDrawable(i);
            }
            a.recycle();
        }
        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ListViewHolder vh = new ListViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_list, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            holder.avatar.setImageDrawable(avatars[position % avatars.length]);
            holder.name.setText(names[position % names.length]);
            holder.des.setText(des[position % des.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        class ListViewHolder extends RecyclerView.ViewHolder {
            public ImageView avatar;
            public TextView name;
            public TextView des;
            public ListViewHolder(View itemView) {
                super(itemView);
                avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
                name = (TextView)itemView.findViewById(R.id.list_title);
                des = (TextView)itemView.findViewById(R.id.list_desc);
            }
        }
    }
}
