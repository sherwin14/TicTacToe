package pitao.sherwin.com.tictactoe;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Android on 10/6/2015.
 */
public class GridViewAdapter extends BaseAdapter {

    Context context;
    public int[]    imgThumb;
    public GridViewAdapter(Context context, int[] imgThumb) {
        this.context = context;
        this.imgThumb = imgThumb;
    }


    @Override
    public Object getItem(int i) {
        return imgThumb[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return imgThumb.length;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.player_layout,viewGroup,false);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.imgThumb);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        final ImageView imageView = holder.img;
        imageView.setImageResource(imgThumb[i]);



        return view;
    }

    public static class ViewHolder {
        ImageView img;
    }
}
