package com.example.administrator.myapplication.adatper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.Video;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HomeAdatper extends RecyclerView.Adapter<HomeAdatper.HomeViewHolder>{


    private List<Video> arrayList;
    private LayoutInflater mInflater;
    private OnItemClickListenner listenner;
    private Context mContext;
    public void setOnItemClickListenner(OnItemClickListenner listenner)
    {
        this.listenner =listenner;
    }

    public HomeAdatper(Context context, List<Video> mList)
    {
        this.arrayList =mList;
        this.mContext = context;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.recyleview_text,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(arrayList.get(position).getImgpath().replace("\r\n", ""))
                .into(holder.mImageVideo);

        Picasso.with(mContext)
                .load(arrayList.get(position).getAvatarurl())
                .into(holder.mTouxiang);
        holder.mName.setText(arrayList.get(position).getNickname());
        holder.mTitle.setText(arrayList.get(position).getTitle());
        holder.mGuankan.setText(" * "+arrayList.get(position).getKan_count());
        holder.mDate.setText(" *"+arrayList.get(position).getLoca_date());
    }

    @Override
    public int getItemCount() {
        if(null==arrayList) return 0;
        return arrayList.size();
    }

     class HomeViewHolder extends RecyclerView.ViewHolder
    {

        private TextView mName;
        private ImageView mImageVideo;
        private ImageView mTouxiang;
        private TextView mTitle;
        private TextView mGuankan;
        private TextView mDate;
        public HomeViewHolder(View itemView) {
            super(itemView);
           // mText = itemView.findViewById(R.id.recycler_text);
            mImageVideo = itemView.findViewById(R.id.video_img);
            mTouxiang = itemView.findViewById(R.id.touxiang);
            mName = itemView.findViewById(R.id.myname);
            mTitle = itemView.findViewById(R.id.title_video);
            mGuankan = itemView.findViewById(R.id.guankan_video);
            mDate = itemView.findViewById(R.id.date_video);
            mImageVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listenner != null)
                    {
                        listenner.OnClick(v,getLayoutPosition(),arrayList.get(getLayoutPosition()).getImgpath());
                    }

                }
            });
        }
    }
    public interface OnItemClickListenner
    {
        void OnClick(View view, int position, String city);
    }
}
