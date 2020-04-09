package de.ur.mi.android.u05todolistemitdatum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ToDoListAdapter extends ArrayAdapter<TaskItem> {

    private ArrayList<TaskItem> todoList;
    private Context context;

    public ToDoListAdapter(Context context, ArrayList<TaskItem> todoList) {
        super(context, R.layout.task_list_item, todoList);

        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.task_list_item, null);
        }

        TaskItem taskItem = todoList.get(position);

        if(taskItem != null){
            TextView tvTaskItemText = v.findViewById(R.id.tv_task_item_text);
            TextView tvTaskItemDate = v.findViewById(R.id.tv_task_item_date);

            tvTaskItemText.setText(taskItem.getTask());
            tvTaskItemDate.setText(taskItem.getFormattedDate());
        }
        return v;
    }
}
