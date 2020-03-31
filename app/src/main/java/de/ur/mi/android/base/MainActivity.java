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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText input;
    private Button addButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupViews();

    }

    private void findViews() {
        input = findViewById(R.id.todo_editText);
        addButton = findViewById(R.id.todo_button);
        listView = findViewById(R.id.todo_listView);
    }

    private void setupViews() {
        todoList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = input.getText().toString().trim();

                if (!newTask.isEmpty()) {
                    todoList.add(newTask);
                    adapter.notifyDataSetChanged();
                    input.setText("");
                }
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
        //Code
    }
}
