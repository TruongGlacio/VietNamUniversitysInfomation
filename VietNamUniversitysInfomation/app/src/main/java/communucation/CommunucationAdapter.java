package communucation;

/**
 * Created by Buixu on 29/08/2016.
 */
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.buixu.thongtintruongdaihoctaivietnam.R;

import java.util.ArrayList;


public class CommunucationAdapter extends ArrayAdapter<Communucation>{
    private MediaPlayer mediaPlayer;
    private Context context;
    private int textViewResourceId;
    private ArrayList<Communucation> list;

    public CommunucationAdapter(Context context, int textViewResourceId,ArrayList<Communucation> list) {
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
        final Communucation communucation =  list.get(position);
        if (communucation != null){
            TextView vietnam = (TextView) v.findViewById(R.id.vietnam);
            vietnam.setText(communucation.getVietnam());
            TextView english = (TextView) v.findViewById(R.id.english);
            english.setText(communucation.getEnglish());
            ImageButton speak = (ImageButton) v.findViewById(R.id.speak);
            speak.setImageResource(R.drawable.mutebutton);
            speak.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(context, communucation.getSpeak());
                    mediaPlayer.start();
                }
            });
        }
        return v;
    }

}

