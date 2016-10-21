package ro.cs.upb.osp.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by razvan on 07.07.2015.
 */
public class MenuAdapter extends BaseAdapter {
    private List<MyMenuItem> items;
    private Context context;

    public MenuAdapter(Context context, List<MyMenuItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < this.items.size())
            return this.items.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        /* 
         * In Android displayed items are created by the system from Views.
		 * A View can contain other Views.
		 * The purpose of an adapter is to create the Views displayed in a list or spinner.
		 * A ListView must be given the adapter that builds each View the list might display.
		 * The ListView then calls the getView method of the adapter for each item that might
		 * be on the screen.
		 */

		/*
		 * The parameters getView receives are as follows:
		 * position = the index of the item to be displayed
		 * convertView = the previous View displayed in this position, can be null
		 * parent = reference to the ListView or Spinner the created view will be attached to
		 */
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);

//            This works too but is longer
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService
//                    (Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.list_item, null);
        }

        MyMenuItem currentItem = items.get(position);

		/* LayoutInflater is a system service that builds Views from layouts */
		/* If you receive a non-null view, you can reuse it to reduce cost of redrawing */
		/* TODO: get the layout inflater system service and inflate the list_item view */

        TextView itemName = (TextView) convertView.findViewById(R.id.item_name);
        TextView itemDescription = (TextView) convertView.findViewById(R.id.item_description);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.item_price);


		/* After the view is built, update the layout TextViews to display the correct text */
		/* TODO: set the text in the TextViews inside the newly inflated view */

        itemName.setText(currentItem.getName());
        itemDescription.setText(currentItem.getDesc());
        itemPrice.setText(currentItem.getPrice());


		/* return the new view */
        return convertView;
    }
}
