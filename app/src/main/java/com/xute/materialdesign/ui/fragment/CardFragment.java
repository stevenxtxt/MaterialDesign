package com.xute.materialdesign.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.icu.text.DisplayContext;
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
import com.xute.materialdesign.ui.activity.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xute on 2016/11/24.
 */

public class CardFragment extends Fragment {
    @BindView(R.id.rv_list) RecyclerView rvList;

    private CardAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, null);
        ButterKnife.bind(this, view);

        adapter = new CardAdapter(getActivity());
        rvList.setAdapter(adapter);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    static class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

        private static final int LENGTH = 18;
        private String[] names;
        private String[] des;
        private Drawable[] avatars;

        private OnItemClickListener onItemClickListener;

        public CardAdapter(Context context) {
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
        public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardViewHolder vh = new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(CardViewHolder holder, final int position) {
            holder.cardImage.setImageDrawable(avatars[position % avatars.length]);
            holder.cardTitle.setText(names[position % names.length]);
            holder.cardText.setText(des[position % des.length]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        class CardViewHolder extends RecyclerView.ViewHolder {

            ImageView cardImage;
            TextView cardText;
            TextView cardTitle;
            public CardViewHolder(View itemView) {
                super(itemView);
                cardImage = (ImageView) itemView.findViewById(R.id.card_image);
                cardTitle = (TextView) itemView.findViewById(R.id.card_title);
                cardText = (TextView) itemView.findViewById(R.id.card_text);
            }
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }
    }
}
