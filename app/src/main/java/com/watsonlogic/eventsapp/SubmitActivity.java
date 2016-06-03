package com.watsonlogic.eventsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class SubmitActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int COLLAPSING_TOOL_BAR = 0;
    private static final int TOOL_BAR = 1;
    private static final int RESTORE_APPBAR_LAYOUT = 2;
    private static final int COMPRESS_APPBAR_LAYOUT = 3;
    private static final int SET_ADDPHOTOFAB_INVISIBLE = 4;
    private static final int SET_ADDPHOTOFAB_VISIBLE = 5;

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    private CoordinatorLayout.LayoutParams restoreAppbarLayout;

    private Drawer drawer;
    private FloatingActionButton fab;

    private AccountHeader accountHeader;

    private PrimaryDrawerItem item1;
    private SecondaryDrawerItem item2;
    private SecondaryDrawerItem item3;
    private SecondaryDrawerItem item4;
    private SecondaryDrawerItem item5;

    private long item1Id;
    private long item2Id;
    private long item3Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
    }

    private void setWidgets() {
        setFab();
        setToolbar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setToolbar() {
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        restoreAppbarLayout = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Submit Event");

    }


    private void setFab() {
        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
    }

    private void expandAppbarLayout(boolean b) {
        appBarLayout.setExpanded(b, true);
    }


    private void setSelectedDrawerItem() {
        drawer.setSelection(0); //sets the default selected item, which should be the first item on initial app load!
    }

    private void setVisibleAddPhotoFab() {
        if (fab.getVisibility() == View.GONE) {
            fab.setVisibility(View.VISIBLE);
        }
    }

    private void setInvisibleAddPhotoFab() {
        if (fab.getVisibility() != View.GONE) {
            fab.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private boolean exitAndLoseData = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                //TODO:display alert
                new AlertDialog.Builder(SubmitActivity.this)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to exit and lose this event")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            exitAndLoseData = true;
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            exitAndLoseData = false;
                        }
                    })
                    .show();
        }
        if(exitAndLoseData){
            return super.onOptionsItemSelected(item);
        } else {
            return false;
        }
    }
}
