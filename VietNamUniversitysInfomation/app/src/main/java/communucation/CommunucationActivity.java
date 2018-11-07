package communucation;

/**
 * Created by Buixu on 29/08/2016.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.buixu.thongtintruongdaihoctaivietnam.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import place.Restaurant;
import place.Travel;
import start.ActionMenu;
import start.NavDrawerItem;
import start.NavDrawerListAdapter;

public class CommunucationActivity extends Activity {

    int language;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    ArrayList<Communucation> communution;
    Communucation a;
    CommunucationAdapter adapterCommunution = null;
    ListView lv = null;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communucation);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        language = packageFromCaller.getInt("language");

        mTitle = mDrawerTitle = getTitle();
        setTitle("Communucation");
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
                .getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
                .getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
                .getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
                .getResourceId(3, -1)));
        navMenuIcons.recycle();

        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case 0:
                        Intent myIntent = new Intent(CommunucationActivity.this,
                                ActionMenu.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("language", language);
                        myIntent.putExtra("MyPackage", bundle);
                        startActivity(myIntent);
                        break;
                    case 1:
                        Intent myIntent1 = new Intent(CommunucationActivity.this,
                                Restaurant.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("language", language);
                        myIntent1.putExtra("MyPackage", bundle1);
                        startActivity(myIntent1);
                        break;
                    case 2:
                        Intent myIntent2 = new Intent(CommunucationActivity.this,
                                Travel.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("language", language);
                        myIntent2.putExtra("MyPackage", bundle2);
                        startActivity(myIntent2);
                        break;
                    case 3:
                }
            }
        });
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.chuamotcot);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, // nav menu toggle icon
                R.string.app_name, // nav drawer open - description for
                // accessibility
                R.string.app_name // nav drawer close - description for
                // accessibility
        ) {
            @Override
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        lv = (ListView) findViewById(R.id.list_communucation);
        communution = new ArrayList<Communucation>();
        try {
            readJSONFile(R.raw.communucation);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		try {
//			readJSONFile(R.raw.communucation);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        adapterCommunution = new CommunucationAdapter(this,
                R.layout.communucation_list_item, communution);
        lv.setAdapter(adapterCommunution);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.vietnam:
                return true;
            case R.id.english:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.vietnam).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void readJSONFile(int id) throws IOException, JSONException {

        InputStream is = this.getResources().openRawResource(id);
        byte[] buffer = new byte[is.available()];
        while (is.read(buffer) != -1)
            ;
        String jsontext = new String(buffer);
        JSONArray entries = new JSONArray(jsontext);

        int i, q;
        for (i = 0; i < entries.length(); i++) {
            a = new Communucation();
            JSONObject post = entries.getJSONObject(i);
            a.setVietnam(post.getString("vietnam"));
            a.setEnglish(post.getString("english"));
            int resID = getResources().getIdentifier(
                    post.getString("filename"), "raw", getPackageName());
            a.setIcon(resID);
            communution.add(a);

        }

    }

}
