package com.watsonlogic.eventsapp;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private Drawer drawer;

    private AccountHeader accountHeader;

    private PrimaryDrawerItem item1;
    private SecondaryDrawerItem item2;
    private SecondaryDrawerItem item3;
    private SecondaryDrawerItem item4;



    private long item1Id;
    private long item2Id;
    private long item3Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgets();

    }

    private void setWidgets(){
        setToolbar();
        setDrawer();
    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //collapsingToolbarLayout.setTitle(getString(R.string.drawer_item_collapsing_toolbar_drawer));
        collapsingToolbarLayout.setTitle("Latest Events");
    }

    private void setDrawer(){
        setAccountHeader();
        setDrawerItems();
        buildDrawer();
        setSelectedDrawerItem();
    }


    private void setAccountHeader(){
        accountHeader = new AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.tempbackground)
            .addProfiles(
                    new ProfileDrawerItem()
                            .withName("John Doe")
                            .withEmail("johndoe@gmail.com")
                            .withIcon(ContextCompat.getDrawable(this, R.drawable.profile))
            )
            .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                @Override
                public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                    return false;
                }
            })
            .build();
    }

    private void setDrawerItems(){
        item1 = new PrimaryDrawerItem()
                .withName(R.string.drawer_item_browse_events)
                .withIcon(GoogleMaterial.Icon.gmd_event)
                .withBadge("19")
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700))
                .withIdentifier(0);
        //Log.d(TAG, String.valueOf(item1.getIdentifier()));

        item2 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_locate_events)
                .withIcon(GoogleMaterial.Icon.gmd_my_location)
                .withIdentifier(1);
        //Log.d(TAG, String.valueOf(item2.getIdentifier()));

        item3 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_submit_event)
                .withIcon(GoogleMaterial.Icon.gmd_add_circle)
                .withIdentifier(2);

        item4 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_edit_profile)
                .withIcon(GoogleMaterial.Icon.gmd_account_circle)
                .withIdentifier(3);

    }

    private void buildDrawer(){
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withFullscreen(true)
                .withTranslucentStatusBar(true)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        item1,
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentManager m = getSupportFragmentManager();
                        FragmentTransaction t = m.beginTransaction();
                        Fragment f = new Fragment();
                        switch (position) {
                            case 1:
                                f = new BrowseFragment();
                                break;
                            case 2:
                                f = new LocateFragment();
                                break;
                            case 4:
                                f = new SubmitFragment();
                                //startActivity(new Intent(MainActivity.this, SubmitActivity.class));
                                break;
                            case 5:
                                f = new EditFragment();
                                break;
                        }
                        t.replace(R.id.frame_fragments, f);
                        t.commit();
                        return false; //close drawer onclick
                    }
                })
                .build();

        loadBackdrop();
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("https://unsplash.it/600/300/?random").centerCrop().into(imageView);
    }

    private void setSelectedDrawerItem(){
        drawer.setSelection(0); //sets the default selected item, which should be the first item on initial app load!
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
