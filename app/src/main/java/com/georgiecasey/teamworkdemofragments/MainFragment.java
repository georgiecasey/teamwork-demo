package com.georgiecasey.teamworkdemofragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.georgiecasey.teamworkdemofragments.adapters.ProjectsAdapter;
import com.georgiecasey.teamworkdemofragments.api.ApiUtil;
import com.georgiecasey.teamworkdemofragments.api.TeamworkService;
import com.georgiecasey.teamworkdemofragments.model.response.project.Project;
import com.georgiecasey.teamworkdemofragments.model.response.project.Projects;

import java.util.ArrayList;

import retrofit2.Call;

public class MainFragment extends Fragment {
    private ProjectsAdapter mProjectsAdapter;
    private TeamworkService mService;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mService = ApiUtil.getTeamworkService();
        loadProjects();
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Projects");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Projects");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("GAVIN","setUserVisibleHint");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProjectsAdapter = new ProjectsAdapter(getActivity(), new ArrayList<Project>(0), new ProjectsAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                RecyclerListFragment fragment=new RecyclerListFragment();
                Bundle args = new Bundle();
                args.putLong("project_id", id);
                fragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.content, fragment)
                        .hide(MainFragment.this)
                        .addToBackStack("tasklists")
                        .commit();
            }

        });

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mProjectsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void loadProjects() {
        mService.getProjects().enqueue(new retrofit2.Callback<Projects>() {
            @Override
            public void onResponse(retrofit2.Call<Projects> call, retrofit2.Response<Projects> response) {
                if(response.isSuccessful()) {
                    Log.d("MainActivity", "asasasas");
                    mProjectsAdapter.updateProjects(response.body().getProjects());
                }else {
                    int statusCode  = response.code();
                    Log.d("MainActivity", "status code");
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Log.e("MYAPP", "exception", t);
                Log.d("MainActivity", "error loading from API");

            }
        });
    }
}
