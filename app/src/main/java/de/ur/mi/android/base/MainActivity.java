package de.ur.mi.android.base;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText inputText;
    private EditText inputDate;
    private Button addButton;
    private ListView listView;
    private ToDoListAdapter adapter;
    private ArrayList<TaskItem> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupViews();

    }

    private void findViews() {
        inputText = findViewById(R.id.task_editText);
        inputDate = findViewById(R.id.date_editText);
        addButton = findViewById(R.id.todo_button);
        listView = findViewById(R.id.todo_listView);
    }

    private void setupViews() {
        initDateView();
        initListView();
        initButton();
    }

    private void initDateView() {
        inputDate.setFocusable(false);
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePickerDialog().show();
            }
        });
    }

    private void initButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = inputText.getText().toString().trim();
                String date = inputDate.getText().toString().trim();

                if (!task.isEmpty() && !date.isEmpty()) {
                    TaskItem taskItem = new TaskItem(task, date);
                    todoList.add(taskItem);
                    adapter.notifyDataSetChanged();
                    inputText.setText("");
                    inputDate.setText("");
                }
            }
        });
    }

    private void initListView() {
        todoList = new ArrayList<>();
        adapter = new ToDoListAdapter(this, todoList);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private DatePickerDialog createDatePickerDialog(){
        GregorianCalendar today = new GregorianCalendar();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        return new DatePickerDialog(this, this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.GERMANY);
        String dateString = df.format(date.getTime());
        inputDate.setText(dateString);
    }
}
