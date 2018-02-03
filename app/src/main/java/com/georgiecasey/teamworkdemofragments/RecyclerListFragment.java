package com.georgiecasey.teamworkdemofragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.georgiecasey.teamworkdemofragments.adapters.TasklistsAdapter;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.OnStartDragListener;
import com.georgiecasey.teamworkdemofragments.adapters.helpers.SimpleItemTouchHelperCallback;
import com.georgiecasey.teamworkdemofragments.api.ApiUtil;
import com.georgiecasey.teamworkdemofragments.api.TeamworkService;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklist;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklists;

import java.util.ArrayList;

import retrofit2.Call;

public class RecyclerListFragment extends Fragment implements OnStartDragListener {
    private ItemTouchHelper mItemTouchHelper;
    private TasklistsAdapter mTasklistsAdapter;
    private TeamworkService mService;
    private long project_id;

    public RecyclerListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            project_id = getArguments().getLong("project_id");
            Log.d("GAVIN","arguments not null"+project_id);
        } else {
            Log.d("GAVIN","arguments null");
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

        mTasklistsAdapter = new TasklistsAdapter(getActivity(), new ArrayList<Tasklist>(0), this,new TasklistsAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(getActivity(), "Task id is" + id, Toast.LENGTH_SHORT).show();
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
                if(response.isSuccessful()) {
                    mTasklistsAdapter.updateTasklists(response.body().getTasklists());
                }else {
                    int statusCode  = response.code();
                    Log.d("MainActivity", "status code");
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Tasklists> call, Throwable t) {
                Log.e("MYAPP", "exception", t);
                Log.d("MainActivity", "error loading from API");

            }
        });
    }
}
