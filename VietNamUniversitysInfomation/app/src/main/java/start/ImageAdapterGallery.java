package start;

/**
 * Created by Buixu on 29/08/2016.
 */

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapterGallery extends BaseAdapter{
    private Activity context;
    private static ImageView imageView;
    private List<Drawable> plotsImages;
    private static ViewHolder holder;

    //Hàm khởi tạo đối tượng
    public ImageAdapterGallery(Activity context, List<Drawable> plotsImages) {
        this.context = context;
        this.plotsImages = plotsImages;
    }

    //�?ịnh nghĩa lại các hàm get lấy v�? các giá trị cần thiết
    @Override
    public int getCount() {
        return plotsImages.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //�?ịnh nghĩa lại hàm getView và kết quả trả v�? là 1 view để hiển thị
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //Tạo đối tượng
            holder = new ViewHolder();
            imageView = new ImageView(this.context);
            //Thiết lập khoảng cách vùng đệm xung quanh
            imageView.setPadding(3, 3, 3, 3);
            convertView = imageView;
            holder.imageView = imageView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Thiêt lập nguồn ảnh
        holder.imageView.setImageDrawable(plotsImages.get(position));
        //Chỉnh kiểu ảnh quy mô hình ảnh
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.imageView.setLayoutParams(new Gallery.LayoutParams(150, 90));
        return imageView;
    }

    //Lớp con ViewHolder quản lý hình ảnh
    private static class ViewHolder {
        ImageView imageView;
    }

}
