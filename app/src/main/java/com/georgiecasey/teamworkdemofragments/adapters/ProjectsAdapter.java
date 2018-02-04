package com.georgiecasey.teamworkdemofragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.georgiecasey.teamworkdemofragments.R;
import com.georgiecasey.teamworkdemofragments.model.response.project.Project;

import java.util.List;

/**
 * Created by georgiecasey on 01/02/2018.
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {
    private List<Project> mProjects;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTv;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.text);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Project item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getId());

            notifyDataSetChanged();
        }
    }

    public ProjectsAdapter(Context context, List<Project> posts, PostItemListener itemListener) {
        mProjects = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public ProjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.project_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectsAdapter.ViewHolder holder, int position) {
        Project item = mProjects.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public void updateProjects(List<Project> items) {
        mProjects = items;
        notifyDataSetChanged();
    }

    private Project getItem(int adapterPosition) {
        return mProjects.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}
