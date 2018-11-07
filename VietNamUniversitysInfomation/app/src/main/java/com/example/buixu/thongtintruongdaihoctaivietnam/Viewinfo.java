package com.example.buixu.thongtintruongdaihoctaivietnam;

/**
 * Created by Buixu on 29/08/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import place.Restaurant;
import place.Travel;

public class Viewinfo extends Activity {
    ImageView imageview;
    TextView info;
    Bundle packageFromCaller;
    int language = 1;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_info);
        imageview=(ImageView) findViewById(R.id.imageView1);
        info=(TextView) findViewById(R.id.textView3);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Intent callerIntent = getIntent();
        packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        int resID = getResources().getIdentifier(packageFromCaller.getString("nameicon") , "drawable", getPackageName());
        Drawable drawable;
      //  drawable=getResources().getDrawable(resID);
        drawable = ContextCompat.getDrawable(this, R.drawable.hoankiem);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
                (int) (bd.getIntrinsicHeight() * 0.9),
                (int) (bd.getIntrinsicWidth() * 0.7), false);

        imageview.setImageBitmap(b);
        imageview.setScaleType(ScaleType.FIT_XY);
        info.setText(packageFromCaller.getString("info"));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
        };

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.location:
                Intent myIntent = new Intent(Viewinfo.this, MapsActivity.class);
                myIntent.putExtra("MyPackage", packageFromCaller);
                startActivity(myIntent);
                return true;
            case android.R.id.home:
                if (packageFromCaller.getInt("resouce") == 2) {
                    Intent myIntent1 = new Intent(Viewinfo.this, Restaurant.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("language", packageFromCaller.getInt("language"));
                    myIntent1.putExtra("MyPackage", bundle1);
                    startActivity(myIntent1);
                }
                if (packageFromCaller.getInt("resouce") == 1) {
                    Intent myIntent1 = new Intent(Viewinfo.this, Travel.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("language", packageFromCaller.getInt("language"));
                    myIntent1.putExtra("MyPackage", bundle1);
                    startActivity(myIntent1);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
