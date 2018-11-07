package place;

/**
 * Created by Buixu on 29/08/2016.
 */
import android.graphics.drawable.Drawable;

public class Place {
    private String name;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }


    private String address;

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    private double longitude;
    private double latitude;
    public double getLongitude(){
        return longitude;
    }
    public void setLongitude(double longitude){
        this.longitude=longitude;
    }
    public double getLatitude(){
        return latitude;
    }
    public void setLatitude(double latitude){
        this.latitude=latitude;
    }

    private String info;

    public String getInfo(){
        return info;
    }
    public void setInfo(String info){
        this.info=info;
    }

    private Drawable icon;
    public Drawable getIcon(){
        return icon;
    }
    public void setIcon(Drawable icon){
        this.icon=icon;
    }

    private String nameIcon;
    public String getNameIcon(){
        return nameIcon;
    }
    public void setNameIcon(String nameIcon){
        this.nameIcon=nameIcon;
    }

}
