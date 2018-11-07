package start;

/**
 * Created by Buixu on 29/08/2016.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.example.buixu.thongtintruongdaihoctaivietnam.R;

import java.util.ArrayList;
import java.util.List;

import communucation.CommunucationActivity;
import place.Restaurant;
import place.Travel;

public class ActionMenu extends Activity {
    private TextView infoHanoi, selectedNameImage;
    private ImageView selectedImageView;
    private ImageView leftArrowImageView;
    private ImageView rightArrowImageView;
    private Gallery gallery;
    private int selectedImagePosition = 0;
    private List<Drawable> drawables;
    private List<String> nameSelect;
    private ImageAdapterGallery galImageAdapter;
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

    int language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionmenu);
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        language = packageFromCaller.getInt("language");

        getDrawablesList();
        setupUI();

        mTitle = mDrawerTitle = getTitle();
        setTitle("Home");
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

        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case 0:
                        break;
                    case 1:
                        Intent myIntent1 = new Intent(ActionMenu.this,
                                Restaurant.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("language", language);
                        myIntent1.putExtra("MyPackage", bundle1);
                        startActivity(myIntent1);
                        break;
                    case 2:
                        Intent myIntent2 = new Intent(ActionMenu.this, Travel.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("language", language);
                        myIntent2.putExtra("MyPackage", bundle2);
                        startActivity(myIntent2);
                        break;
                    case 3:
                        Intent myIntent3 = new Intent(ActionMenu.this,
                                CommunucationActivity.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("language", language);
                        myIntent3.putExtra("MyPackage", bundle3);
                        startActivity(myIntent3);
                        break;
                }
            }
        });
        // enabling action bar app icon and behaving it as toggle button
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
    }

    private void setupUI() {
        infoHanoi = (TextView) findViewById(R.id.info_hanoi);
        selectedImageView = (ImageView) findViewById(R.id.selected_imageview);
        leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
        rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
        gallery = (Gallery) findViewById(R.id.gallery);
        selectedNameImage = (TextView) findViewById(R.id.select_name);
        if (language == 1)
            infoHanoi.setText("Những địa điểm bạn nên đến ở hà nội");
        else
            infoHanoi.setText("Places you should visit in Hanoi");

        // Tạo 1 lắng nge từ nút bên trái
        leftArrowImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImagePosition > 0) {
                    --selectedImagePosition;
                }
                gallery.setSelection(selectedImagePosition, false);
            }
        });

        // Tạo 1 lắng nge từ nút bên phải
        rightArrowImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImagePosition < drawables.size() - 1) {
                    ++selectedImagePosition;
                }
                gallery.setSelection(selectedImagePosition, false);
            }
        });

        // Tạo lắng nge từ đối tượng Gallery
        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                selectedImagePosition = pos;
                // Kiểm tra vị trí ảnh trong danh sách hình ảnh để thiết lập 2
                // nút mũi tên như mong muốn
                if (selectedImagePosition > 0
                        && selectedImagePosition < drawables.size() - 1) {
                    leftArrowImageView.setImageDrawable(getResources()
                            .getDrawable(R.drawable.arrow_left_enabled));
                    rightArrowImageView.setImageDrawable(getResources()
                            .getDrawable(R.drawable.arrow_right_enabled));
                } else if (selectedImagePosition == 0) {
                    leftArrowImageView.setImageDrawable(getResources()
                            .getDrawable(R.drawable.arrow_left_disabled));
                } else if (selectedImagePosition == drawables.size() - 1) {
                    rightArrowImageView.setImageDrawable(getResources()
                            .getDrawable(R.drawable.arrow_right_disabled));
                }
                // Thực hiện cập nhật lại vi�?n
                changeBorderForSelectedImage(selectedImagePosition);
                // Cập nhật hình ảnh đc lựa ch�?n
                setSelectedImage(selectedImagePosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        // Tạo đối tượng ImageAdapterGallery
        galImageAdapter = new ImageAdapterGallery(this, drawables);
        // Thiết lập nguồn và đổ vào gallery
        gallery.setAdapter(galImageAdapter);
        if (drawables.size() > 0) {
            gallery.setSelection(selectedImagePosition, false);
        }

        if (drawables.size() == 1) {
            rightArrowImageView.setImageDrawable(getResources().getDrawable(
                    R.drawable.arrow_right_disabled));
        }
    }

    // Thực hiện thay đổi vi�?n cho hình ảnh được lựa ch�?n
    private void changeBorderForSelectedImage(int selectedItemPos) {
        int count = gallery.getChildCount();
        for (int i = 0; i < count; i++) {
            ImageView imageView = (ImageView) gallery.getChildAt(i);
            imageView.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.image_border));
            imageView.setPadding(3, 3, 3, 3);
        }

        ImageView imageView = (ImageView) gallery.getSelectedView();
        imageView.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.selected_image_border));
        imageView.setPadding(3, 3, 3, 3);
    }

    // Lấy danh sách các hình ảnh đưa vào mảng để quản lý
    private void getDrawablesList() {
        drawables = new ArrayList<Drawable>();
        nameSelect = new ArrayList<String>();
        drawables.add(getResources().getDrawable(R.drawable.langbac4));
        nameSelect.add("Lang Bac");
        drawables.add(getResources().getDrawable(R.drawable.chuamotcot1));
        nameSelect.add("Chua Mot Cot");
        drawables.add(getResources().getDrawable(R.drawable.hoangthanhthanglong5));
        nameSelect.add("Hoang Thanh Thang Long");
        drawables.add(getResources().getDrawable(R.drawable.hoguom1));
        nameSelect.add("Ho Guom");
        drawables.add(getResources().getDrawable(R.drawable.hotay3));
        nameSelect.add("Ho Tay");
        drawables.add(getResources().getDrawable(R.drawable.vanmieu1));
        nameSelect.add("Van Mieu");

    }

    // Thiết lập hình ảnh lựa ch�?n hiển thị trên ImageView chính
    private void setSelectedImage(int selectedImagePosition) {
        BitmapDrawable bd = (BitmapDrawable) drawables
                .get(selectedImagePosition);
        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
                (int) (bd.getIntrinsicHeight() * 0.9),
                (int) (bd.getIntrinsicWidth() * 0.7), false);
        selectedImageView.setImageBitmap(b);
        selectedImageView.setScaleType(ScaleType.FIT_XY);
        selectedNameImage.setText(nameSelect.get(selectedImagePosition));
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
                language=1;
                infoHanoi.setText("Những địa điểm bạn nên đến ở hà nội");
                return true;
            case R.id.english:
                language=2;
                infoHanoi.setText("Places you should visit in Hanoi");
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

    @SuppressLint("NewApi")
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
}

