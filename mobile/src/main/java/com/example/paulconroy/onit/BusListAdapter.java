package com.example.paulconroy.onit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paulconroy.onit.model.BusData;

import java.util.List;

/**
 * Created by paulconroy on 17/12/2015.
 */
public class BusListAdapter extends ArrayAdapter<BusData> {

    public BusListAdapter(Context context, int resource,
                          List<BusData> objects) {
        super(context, resource, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(
                    R.layout.bus_list_item, parent, false);
        }

        BusData bus = getItem(position);
        if (bus != null) {
            TextView routeLabel = (TextView) convertView
                    .findViewById(R.id.routeLabel);
            TextView destinationLabel = (TextView) convertView
                    .findViewById(R.id.destinationlabel);
            TextView dueLabel = (TextView) convertView
                    .findViewById(R.id.dueLabel);


            routeLabel.setText(bus.getRoute());
            destinationLabel.setText(bus.getDestination());
            dueLabel.setText(bus.getDuetime());

        }

        return convertView;
    }
}
