package test.listviewtest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Stanley Ung on 3/2/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context c, List<RowItem> l) {
        this.context = c;
        this. rowItems = l;
    }

    @Override
    public int getCount() { return rowItems.size(); }

    @Override
    public Object getItem(int index) { return rowItems.get(index); }

    @Override
    public long getItemId(int index) { return rowItems.indexOf(getItem(index)); }

    private class ViewHolder {
        ImageView pokemon_pic;
        TextView pokemon_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.pokemon_name = (TextView) convertView.findViewById(R.id.pokemon_name);
            holder.pokemon_pic = (ImageView) convertView.findViewById(R.id.pokemon_pics);

            RowItem row_pos = rowItems.get(position);

            holder.pokemon_pic.setImageResource(row_pos.getPokemon_pic_id());
            holder.pokemon_name.setText(row_pos.getPokemon_name());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
