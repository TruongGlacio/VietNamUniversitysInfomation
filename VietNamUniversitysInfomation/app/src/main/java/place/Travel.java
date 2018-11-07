package place;

/**
 * Created by Buixu on 29/08/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buixu.thongtintruongdaihoctaivietnam.R;
import com.example.buixu.thongtintruongdaihoctaivietnam.Viewinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import communucation.CommunucationActivity;
import start.ActionMenu;
import start.NavDrawerItem;
import start.NavDrawerListAdapter;

public class Travel extends Activity{

    int language;
    int resouce=1;

    Place travel;
    JSONArray entries;
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


    ArrayList<Place> place;
    PlaceAdapter adapterPlace=null;
    ListView lv=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        language=packageFromCaller.getInt("language");

        mTitle = mDrawerTitle = getTitle();
        setTitle("Travel");
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navMenuIcons.recycle();

        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    case 0:
                        Intent myIntent=new Intent(Travel.this, ActionMenu.class);
                        Bundle bundle=new Bundle();
                        bundle.putInt("language", language);
                        myIntent.putExtra("MyPackage", bundle);
                        startActivity(myIntent);
                        break;
                    case 1:
                        Intent myIntent1=new Intent(Travel.this, Restaurant.class);
                        Bundle bundle1=new Bundle();
                        bundle1.putInt("language", language);
                        myIntent1.putExtra("MyPackage", bundle1);
                        startActivity(myIntent1);
                        break;
                    case 2:

                        break;
                    case 3:
                        Intent myIntent3=new Intent(Travel.this, CommunucationActivity.class);
                        Bundle bundle3=new Bundle();
                        bundle3.putInt("language", language);
                        myIntent3.putExtra("MyPackage", bundle3);
                        startActivity(myIntent3);
                        break;
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
        lv=(ListView) findViewById(R.id.list_place);
        place=new ArrayList<Place>();
        try {
            if(language==1){
                try{
                    readJSONFile1(R.raw.travel);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            } else if(language==2){
               try{
                   readJSONFile1(R.raw.travel_e);
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
            }
        }  catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        adapterPlace= new PlaceAdapter(this, R.layout.place_list_item, place);
        lv.setAdapter(adapterPlace);

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent myIntent=new Intent(Travel.this, Viewinfo.class);
                Bundle bundle=new Bundle();
                bundle.putInt("language", language);
                bundle.putInt("resouce", resouce);
                bundle.putString("nameicon", place.get(arg2).getNameIcon());
                bundle.putDouble("latitude", place.get(arg2).getLatitude());
                bundle.putDouble("longitude", place.get(arg2).getLongitude());
                bundle.putString("info", place.get(arg2).getInfo());
                myIntent.putExtra("MyPackage", bundle);
                startActivity(myIntent);
            }

        });
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
                while(place.size()!=0){
                    place.remove(0);
                }
                language=1;
                try {
                    readJSONFile1(R.raw.travel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapterPlace.notifyDataSetChanged();
                return true;
            case R.id.english:
                while(place.size()!=0){
                    place.remove(0);
                }
                language=2;
                try {
                    readJSONFile1(R.raw.travel_e);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapterPlace.notifyDataSetChanged();
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

    public void readJSONFile(int id) throws IOException, JSONException{
        InputStream inputStream = getResources().openRawResource(id);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(),"khong doc duoc du lieu json",Toast.LENGTH_SHORT).show();
        }
        //Log.v("Text Data", byteArrayOutputStream.toString());
        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(byteArrayOutputStream.toString());
            JSONObject jObjectResult = jObject.getJSONObject("Categories");
            JSONArray jArray = jObjectResult.getJSONArray("CacdiemdulichnoibatoHaNoi");
            String name = "";
            String Address = "";
            String info="";
            Double latitude=0.0;
            Double longitude=0.0;
            String nameicon="";

            //    ArrayList<String[]> data = new ArrayList<String[]>();
            for (int i = 0; i < jArray.length(); i++) {
                travel = new Place();

                try{
                    name = jArray.getJSONObject(i).getString("name");
                    Address = jArray.getJSONObject(i).getString("address");
                    info=jArray.getJSONObject(i).getString("info");
                    String latitude_string=jArray.getJSONObject(i).getString("latitude");
                    String longitude_string=jArray.getJSONObject(i).getString("longitude");
                    nameicon=jArray.getJSONObject(i).getString("nameicon");
                    try{
                        latitude=Double.parseDouble(latitude_string);
                        longitude=Double.parseDouble(longitude_string);

                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getBaseContext(),"Khong lay duoc du lieu Toa do",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),"khong doc duoc du lieu json",Toast.LENGTH_SHORT).show();

                }

                travel.setName(name+"");
                travel.setAddress(Address+"");
                travel.setInfo(info+"");
                travel.setLatitude(latitude);
                travel.setLongitude(longitude);
                travel.setNameIcon(nameicon+"");
                Log.v("name", name);
                Log.v("Adress", Address);
                //   data.add(new String[] { name, name });
                try {
                    int resID = getResources().getIdentifier(nameicon, "drawable", getPackageName());
                    Drawable drawable;
                    //drawable = getResources().getDrawable(resID,null);
                    drawable = ContextCompat.getDrawable(this, R.drawable.hoankiem);
                    travel.setIcon(drawable);
                    place.add(travel);
                }
                catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),"Khong hien thi duoc chuoi Json",Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readJSONFile1(int id) throws IOException, JSONException
    {

        InputStream is = this.getResources().openRawResource(id);
        byte[] buffer = new byte[is.available()];
        while (is.read(buffer) != -1)
            ;
        String jsontext = new String(buffer);
        JSONArray entries = new JSONArray(jsontext);

        int i,q;
        for (i=0;i<entries.length();i++)
        {
            travel=new Place();
            JSONObject post = entries.getJSONObject(i);
            travel.setName(post.getString("name"));
            travel.setAddress(post.getString("address"));
            travel.setInfo(post.getString("info"));
            travel.setLatitude(post.getDouble("latitude"));
            travel.setLongitude(post.getDouble("longitude"));
            travel.setNameIcon(post.getString("nameicon"));
            int resID = getResources().getIdentifier(post.getString("nameicon") , "drawable", getPackageName());
            Drawable drawable;
            drawable=getResources().getDrawable(resID);
            travel.setIcon(drawable);
            place.add(travel);


        }


    }

}

