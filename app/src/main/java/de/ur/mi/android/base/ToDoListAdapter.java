package de.ur.mi.android.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoListAdapter extends ArrayAdapter<TaskItem> {

    private ArrayList<TaskItem> taskList;
    private Context context;

    public ToDoListAdapter(Context context, ArrayList<TaskItem> taskList) {
        super(context, R.layout.task_list_item, taskList);

        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            v = layoutInflater.inflate(R.layout.task_list_item, null);
        }

        TaskItem taskItem = taskList.get(position);

        if(taskItem != null){
            TextView taskItemText = v.findViewById(R.id.taskItemText_textView);
            TextView taskItemDate = v.findViewById(R.id.taskItemDate_textView);

            taskItemText.setText(taskItem.getTask());
            taskItemDate.setText(taskItem.getFormattedDate());
        }
        return v;
    }
}
