package place;

/**
 * Created by Buixu on 29/08/2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.buixu.thongtintruongdaihoctaivietnam.R;

import java.util.ArrayList;


public class PlaceAdapter extends ArrayAdapter<Place> {

    private Context context;
    private int textViewResourceId;
    private ArrayList<Place> list;

    public PlaceAdapter(Context context, int textViewResourceId,
                        ArrayList<Place> list) {
        super(context, textViewResourceId,list);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.list = list;
    }


    @Override
    public View getView(int position, View ht, ViewGroup parent) {
        View v = ht;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(textViewResourceId, null);
        }
        final Place place =  list.get(position);
        if (place != null){
            TextView name = (TextView) v.findViewById(R.id.placeName);
            name.setText(place.getName());
            TextView info = (TextView) v.findViewById(R.id.placeInfo);
            info.setText(place.getAddress());
            ImageView icon= (ImageView) v.findViewById(R.id.placeImage);
            BitmapDrawable bd = (BitmapDrawable) place.getIcon();
            Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
                    (int) (bd.getIntrinsicHeight() * 0.9),
                    (int) (bd.getIntrinsicWidth() * 0.7), false);
            icon.setImageBitmap(b);
            icon.setScaleType(ScaleType.FIT_XY);
        }
        return v;
    }

}
