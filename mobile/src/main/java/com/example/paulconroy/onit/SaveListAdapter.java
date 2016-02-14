package com.example.paulconroy.onit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paulconroy.onit.model.StopSave;

import java.util.List;

/**
 * Created by paulconroy on 17/12/2015.
 */
public class SaveListAdapter extends ArrayAdapter<StopSave> {

    public SaveListAdapter(Context context, int resource,
                           List<StopSave> objects) {
        super(context, resource, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(
                    R.layout.bus_list_fav_item, parent, false);
        }

        StopSave save = getItem(position);
        if (save != null) {
            TextView numberLabel = (TextView) convertView
                    .findViewById(R.id.routeLabel);
            TextView destinationLabel = (TextView) convertView
                    .findViewById(R.id.destinationlabel);


            numberLabel.setText(save.getNumber());
            destinationLabel.setText(save.getLocation());

        }

        return convertView;
    }
}
