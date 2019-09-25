package com.example.cbpierre.epromonitor;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.cbpierre.epromonitor.fragments.ChoiceContactGHFragment;
import com.example.cbpierre.epromonitor.fragments.ContactDetailFragment;
import com.example.cbpierre.epromonitor.fragments.ContactFragment;
import com.example.cbpierre.epromonitor.fragments.DimancheGHFragment;
import com.example.cbpierre.epromonitor.fragments.EtablissementFragment;
import com.example.cbpierre.epromonitor.fragments.EtabsDetailFragment;
import com.example.cbpierre.epromonitor.fragments.EtabsRegisterFragment;
import com.example.cbpierre.epromonitor.fragments.GHFragment;
import com.example.cbpierre.epromonitor.fragments.JeudiFragment;
import com.example.cbpierre.epromonitor.fragments.LundiGHFragment;
import com.example.cbpierre.epromonitor.fragments.MardiGHFragment;
import com.example.cbpierre.epromonitor.fragments.MercrediGHFragment;
import com.example.cbpierre.epromonitor.fragments.PaContactFragment;
import com.example.cbpierre.epromonitor.fragments.PlanActionFragment;
import com.example.cbpierre.epromonitor.fragments.PostDetailFragment;
import com.example.cbpierre.epromonitor.fragments.RapportFragment;
import com.example.cbpierre.epromonitor.fragments.SamediFragment;
import com.example.cbpierre.epromonitor.fragments.SignInFragment;
import com.example.cbpierre.epromonitor.fragments.TelechargementFragment;
import com.example.cbpierre.epromonitor.fragments.VendrediGHFragment;

public class MainActivity extends AppCompatActivity implements SignInFragment.OnFragmentInteractionListener, ContactFragment.OnFragmentInteractionListener, ContactDetailFragment.OnContactDetailInteractionListener, PostDetailFragment.OnFragmentInteractionListener, EtabsDetailFragment.OnFragmentInteractionListener, EtabsRegisterFragment.OnFragmentInteractionListener, PlanActionFragment.OnFragmentInteractionListener, PaContactFragment.OnFragmentInteractionListener, LundiGHFragment.OnFragmentInteractionListener, MardiGHFragment.OnFragmentInteractionListener, MercrediGHFragment.OnFragmentInteractionListener, JeudiFragment.OnFragmentInteractionListener, VendrediGHFragment.OnFragmentInteractionListener, SamediFragment.OnFragmentInteractionListener, DimancheGHFragment.OnFragmentInteractionListener, RapportFragment.OnFragmentInteractionListener, ChoiceContactGHFragment.OnFragmentInteractionListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    UserSessionPreferences userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set a toolbar to replace the ActionBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Find our navigation drawer
        nvDrawer = findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_contact_fragment:
                fragmentClass = ContactFragment.class;
                break;
            case R.id.nav_etablissement_fragment:
                fragmentClass = EtablissementFragment.class;
                break;
            case R.id.nav_pa_fragment:
                fragmentClass = PlanActionFragment.class;
                break;
            case R.id.nav_gh_fragment:
                fragmentClass = GHFragment.class;
                break;

            case R.id.nav_user_fragment:
                fragmentClass = PostDetailFragment.class;
                break;

            case R.id.nav_telechargement_fragment:
                fragmentClass = TelechargementFragment.class;
                break;

            case R.id.nav_logout:
                //user session
                userSession = new UserSessionPreferences(getApplicationContext());
                userSession.logoutUser();
                finish();
                break;

            default:
                fragmentClass = ContactFragment.class;

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
            setTitle(menuItem.getTitle());
            // Close the navigation drawer
            mDrawer.closeDrawers();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            drawerToggle = setupDrawerToggle();
            drawerToggle.syncState();
            super.onBackPressed();
        }
    }

    /**
     * onBackPressed
     */


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
