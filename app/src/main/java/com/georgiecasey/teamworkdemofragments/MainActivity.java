package com.georgiecasey.teamworkdemofragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.georgiecasey.teamworkdemofragments.api.ApiUtil;
import com.georgiecasey.teamworkdemofragments.api.TeamworkService;
import com.georgiecasey.teamworkdemofragments.fragments.ProjectsFragment;
import com.georgiecasey.teamworkdemofragments.fragments.TasklistsFragment;
import com.georgiecasey.teamworkdemofragments.model.response.user.Account;
import com.georgiecasey.teamworkdemofragments.model.response.user.User;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TeamworkService mService;
    private ImageView avatar;
    private TextView firstname;
    private TextView teamworkUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtil.getTeamworkService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        avatar=(ImageView) header.findViewById(R.id.avatarImage);
        firstname = (TextView) header.findViewById(R.id.firstName);
        teamworkUrl = (TextView) header.findViewById(R.id.teamworkUrl);

        if (savedInstanceState == null) {
            startProjectsFragment();
        }

        loadTeamworkAccount();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void startProjectsFragment() {
        ProjectsFragment fragment = new ProjectsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_projects) {
            startProjectsFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadTeamworkAccount() {
        mService.getAccount().enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {

                if(response.isSuccessful()) {
                    Account loggedInUser=response.body().getAccount();
                    // prevent glide crash if activity destroyed onresponse
                    if (MainActivity.this != null && !MainActivity.this.isFinishing()) {
                        Glide.with(MainActivity.this)
                                .load(loggedInUser.getAvatarUrl())
                                .apply(new RequestOptions().
                                        fitCenter())
                                .into(avatar);
                    }
                    firstname.setText(loggedInUser.getFirstname());
                    teamworkUrl.setText(loggedInUser.getURL());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


            }
        });
    }
}
