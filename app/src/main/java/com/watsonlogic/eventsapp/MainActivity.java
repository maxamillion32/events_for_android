package com.watsonlogic.eventsapp;

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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
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
        setDrawer();
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
        collapsingToolbarLayout.setTitle("Latest Events");
    }

    private void setDrawer() {
        setAccountHeader();
        setDrawerItems();
        buildDrawer();
        setSelectedDrawerItem();
    }

    private void setFab() {
        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
    }

    private void setAccountHeader() {
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

    private void setDrawerItems() {
        item1 = new PrimaryDrawerItem()
                .withName(R.string.drawer_item_browse_events)
                .withIcon(GoogleMaterial.Icon.gmd_event)
                .withBadge("19")
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700))
                .withIdentifier(0);

        item2 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_locate_events)
                .withIcon(GoogleMaterial.Icon.gmd_my_location)
                .withIdentifier(1);

        item3 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_submit_event)
                .withIcon(GoogleMaterial.Icon.gmd_add_circle)
                .withIdentifier(2);

        item4 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_attending_event)
                .withIcon(GoogleMaterial.Icon.gmd_favorite)
                .withIdentifier(3);

        item5 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.drawer_item_edit_profile)
                .withIcon(GoogleMaterial.Icon.gmd_account_circle)
                .withIdentifier(4);
    }

    private void buildDrawer() {
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
                        item4,
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment f = new Fragment();
                        switch (position) {
                            case 1:
                                f = new BrowseFragment();
                                prepareToLoadFragment(RESTORE_APPBAR_LAYOUT, COLLAPSING_TOOL_BAR, getString(R.string.browse_events), SET_ADDPHOTOFAB_INVISIBLE);
                                break;
                            case 2:
                                f = new LocateFragment();
                                prepareToLoadFragment(COMPRESS_APPBAR_LAYOUT, TOOL_BAR, getString(R.string.locate_events), SET_ADDPHOTOFAB_INVISIBLE);
                                break;
                            case 4:
                                //f = new SubmitFragment();
                                //prepareToLoadFragment(RESTORE_APPBAR_LAYOUT, COLLAPSING_TOOL_BAR, getString(R.string.submit_event), SET_ADDPHOTOFAB_VISIBLE);
                                startActivity(new Intent(MainActivity.this, SubmitActivity.class));
                                return false;
                                //break;
                            case 5:
                                f = new AttendingFragment();
                                prepareToLoadFragment(RESTORE_APPBAR_LAYOUT, COLLAPSING_TOOL_BAR, getString(R.string.attending_event), SET_ADDPHOTOFAB_INVISIBLE);
                                break;
                            case 6:
                                f = new EditFragment();
                                prepareToLoadFragment(RESTORE_APPBAR_LAYOUT, COLLAPSING_TOOL_BAR, getString(R.string.edit_profile), SET_ADDPHOTOFAB_VISIBLE);
                                break;
                        }
                        fragmentTransaction.replace(R.id.frame_fragments, f);
                        fragmentTransaction.commit();

                        return false; //close drawer onclick
                    }
                })
                .build();

        loadBackdrop();
    }

    private void prepareToLoadFragment(int restoreCompressAppBarLayout, int barType, String title, int addPhotoFabVisibility) {
        switch (restoreCompressAppBarLayout) {
            case RESTORE_APPBAR_LAYOUT:
                restoreAppbarLayout();
                break;
            case COMPRESS_APPBAR_LAYOUT:
                compressAndLockAppbarLayout();
        }
        setToolbarTitle(barType, title);
        switch (addPhotoFabVisibility) {
            case SET_ADDPHOTOFAB_INVISIBLE:
                setInvisibleAddPhotoFab();
                break;
            case SET_ADDPHOTOFAB_VISIBLE:
                setVisibleAddPhotoFab();
        }

    }


    private void setToolbarTitle(int bar, String title) {
        switch (bar) {
            case COLLAPSING_TOOL_BAR: {
                collapsingToolbarLayout.setTitleEnabled(true);
                collapsingToolbarLayout.setTitle(title);
                break;
            }
            case TOOL_BAR: {
                if (collapsingToolbarLayout.isTitleEnabled()) {
                    collapsingToolbarLayout.setTitleEnabled(false);
                }
                toolbar.setTitle(title);
            }
        }
    }

    private void expandAppbarLayout(boolean b) {
        appBarLayout.setExpanded(b, true);
    }

    private void compressAndLockAppbarLayout() {
        expandAppbarLayout(false);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()); //dp->px
        CoordinatorLayout.LayoutParams tmp = new CoordinatorLayout.LayoutParams(restoreAppbarLayout); //copy constructor
        tmp.height = px;
        appBarLayout.setLayoutParams(tmp);
    }

    private void restoreAppbarLayout() {
        appBarLayout.setLayoutParams(restoreAppbarLayout);
        expandAppbarLayout(true);
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("http://www.noordschok.nl/wp-content/uploads/2014/12/festival-2.jpg").centerCrop().into(imageView);
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
