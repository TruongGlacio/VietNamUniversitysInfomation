package start;

/**
 * Created by Buixu on 29/08/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;

import com.example.buixu.thongtintruongdaihoctaivietnam.R;

public class Start extends Activity {
    Spinner spinner;
    Button ok;
    int language = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        spinner = (Spinner) findViewById(R.id.spinner1);
        ok = (Button) findViewById(R.id.button1);
        this.setTitle("Start");
        ImageView icon = (ImageView) findViewById(R.id.imageView1);
        BitmapDrawable bd = (BitmapDrawable)  getResources().getDrawable(
                R.drawable.langbac4);
        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
                (int) (bd.getIntrinsicHeight() * 0.9),
                (int) (bd.getIntrinsicWidth() * 0.7), false);
        icon.setImageBitmap(b);
        icon.setScaleType(ScaleType.FIT_XY);
        getActionBar().setIcon(R.drawable.chuamotcot);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.spinner));
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch (arg2) {
                    case 0:
                        language = 1;
                        break;
                    case 1:
                        language = 2;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Start.this, ActionMenu.class);
                Bundle bundle = new Bundle();
                bundle.putInt("language", language);
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
}

