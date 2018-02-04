package com.georgiecasey.teamworkdemofragments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.georgiecasey.teamworkdemofragments.adapters.TasklistsAdapter;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.OnStartDragListener;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.SimpleItemTouchHelperCallback;
import com.georgiecasey.teamworkdemofragments.api.ApiUtil;
import com.georgiecasey.teamworkdemofragments.api.TeamworkService;
import com.georgiecasey.teamworkdemofragments.model.request.tasklists.ReorderTasklists;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklist;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklists;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TasklistsFragment extends Fragment implements OnStartDragListener {
    private ItemTouchHelper mItemTouchHelper;
    private TasklistsAdapter mTasklistsAdapter;
    private TeamworkService mService;
    private long project_id;

    public TasklistsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            project_id = getArguments().getLong("project_id");
        }
        mService = ApiUtil.getTeamworkService();
        loadTasklists();
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tasklists");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tasklists");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTasklistsAdapter = new TasklistsAdapter(getActivity(), new ArrayList<Tasklist>(0), this, new TasklistsAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(getActivity(), "Task id is" + id, Toast.LENGTH_SHORT).show();
            }
        }, new TasklistsAdapter.ItemMoveListener() {
            @Override
            public void onItemMove() {
                reorderTasklists();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mTasklistsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mTasklistsAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public void loadTasklists() {
        mService.getTasklists(project_id).enqueue(new retrofit2.Callback<Tasklists>() {
            @Override
            public void onResponse(retrofit2.Call<Tasklists> call, retrofit2.Response<Tasklists> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Retrofit failed, http code: " + response.code());
                    return;
                }
                mTasklistsAdapter.updateTasklists(response.body().getTasklists());
            }

            @Override
            public void onFailure(Call<Tasklists> call, Throwable t) {
                System.out.println("Retrofit failed: " + t);
            }
        });
    }

    public void reorderTasklists() {
        ReorderTasklists reorderTasklists=new ReorderTasklists();
        for (Tasklist item: mTasklistsAdapter.mTasklists) {
            reorderTasklists.addTasklist(item.getId());
        }

        mService.reorderTasklists(project_id,reorderTasklists).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Retrofit failed, http code: " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Retrofit failed: " + t);
            }
        });
    }
}
