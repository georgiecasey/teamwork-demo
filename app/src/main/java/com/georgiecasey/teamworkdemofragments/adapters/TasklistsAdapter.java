package com.georgiecasey.teamworkdemofragments.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.georgiecasey.teamworkdemofragments.R;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.ItemTouchHelperAdapter;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.ItemTouchHelperViewHolder;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.OnStartDragListener;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by georgiecasey on 31/01/2018.
 */

public class TasklistsAdapter extends RecyclerView.Adapter<TasklistsAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Tasklist> mTasklists=new ArrayList<>();;
    private Context mContext;
    private PostItemListener mItemListener;
    private ItemMoveListener mItemMoveListener;
    private final OnStartDragListener mDragStartListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder, View.OnClickListener {

        public TextView titleTv;
        PostItemListener mItemListener;
        public final ImageView handleView;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.text);
            handleView = (ImageView) itemView.findViewById(R.id.handle);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Tasklist item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getId());

            notifyDataSetChanged();
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            // called when item actually moved, dropped so to say
            itemView.setBackgroundColor(0);
            mItemMoveListener.onItemMove();
        }
    }

    public TasklistsAdapter(Context context, List<Tasklist> posts, OnStartDragListener dragStartListener, PostItemListener itemListener, ItemMoveListener itemMoveListener) {
        mDragStartListener = dragStartListener;
        mContext = context;
        mItemListener = itemListener;
        mItemMoveListener = itemMoveListener;
    }

    @Override
    public TasklistsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.tasklist_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TasklistsAdapter.ViewHolder holder, int position) {
        Tasklist item = mTasklists.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getName());

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasklists.size();
    }

    @Override
    public void onItemDismiss(int position) {
        mTasklists.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTasklists, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public List<Tasklist> getTasklists() {
        return mTasklists;
    }

    public void updateTasklists(List<Tasklist> items) {
        mTasklists = items;
        notifyDataSetChanged();
    }

    private Tasklist getItem(int adapterPosition) {
        return mTasklists.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }

    public interface ItemMoveListener {
        void onItemMove();
    }
}
