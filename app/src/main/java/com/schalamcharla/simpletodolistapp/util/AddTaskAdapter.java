package com.schalamcharla.simpletodolistapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.schalamcharla.simpletodolistapp.R;

import java.util.ArrayList;

/**
 * Created by schalamcharla on 11/18/15.
 */
public class AddTaskAdapter extends ArrayAdapter<ViewTask> {
    public AddTaskAdapter(Context context, ArrayList<ViewTask> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewTask viewTask = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.add_task, parent, false);
        }
        // Lookup view for data population
        TextView viewTaskLabel = (TextView) convertView.findViewById(R.id.tvAddTaskLabel);
        EditText viewTaskValue  = (EditText) convertView.findViewById(R.id.etAddTaskValue);

        // Populate the data into the template view using the data object
        viewTaskLabel.setText(viewTask.getLabel());
        viewTaskValue.setText(viewTask.getValue());

        // Return the completed view to render on screen
        return convertView;
    }
}