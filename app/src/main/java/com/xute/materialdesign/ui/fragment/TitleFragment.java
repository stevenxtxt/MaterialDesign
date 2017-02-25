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

public class TitleFragment extends Fragment {
    @BindView(R.id.rv_list) RecyclerView rvList;

    private TitleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, null);
        ButterKnife.bind(this, view);

        adapter = new TitleAdapter(getActivity());
        rvList.setAdapter(adapter);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {
        private static final int LENGTH = 18;
        private String[] names;
        private Drawable[] avatars;

        public TitleAdapter(Context context) {
            Resources resources = context.getResources();
            names = resources.getStringArray(R.array.places);
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            avatars = new Drawable[a.length()];
            for(int i = 0;i<a.length();i++){
                avatars[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TitleViewHolder vh = new TitleViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_tile, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(TitleViewHolder holder, int position) {
            holder.imageView.setImageDrawable(avatars[position % avatars.length]);
            holder.textView.setText(names[position % names.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        class TitleViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;

            public TitleViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.title_picture);
                textView = (TextView) itemView.findViewById(R.id.title_title);
            }
        }
    }
}
