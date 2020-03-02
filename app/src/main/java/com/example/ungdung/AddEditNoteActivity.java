package com.example.ungdung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.ungdung.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.ungdung.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.ungdung.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.ungdung.EXTRA_PRIORITY";

    private EditText editTexttitle, editTextdescription;
    private NumberPicker numberPickerpriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Anhxa();
    }

    private void Anhxa() {
        editTexttitle = findViewById(R.id.edit_text_title);
        editTextdescription = findViewById(R.id.edit_text_description);
        numberPickerpriority = findViewById(R.id.Number_picker_priority);


        //set value for numberpicker
        numberPickerpriority.setMinValue(1);
        numberPickerpriority.setMaxValue(10);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit_note");
            editTexttitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerpriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add_note");
        }
    }

    private void SaveNote() {
        String title = editTexttitle.getText().toString();
        String description = editTextdescription.getText().toString();
        int priority = numberPickerpriority.getValue();


        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_note:

                SaveNote();

                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}
