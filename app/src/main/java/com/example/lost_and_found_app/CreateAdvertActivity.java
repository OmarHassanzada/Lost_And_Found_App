package com.example.lost_and_found_app;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {

    private RadioGroup radioGroupPostType;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextDescription;
    private EditText editTextDate;
    private EditText editTextLocation;
    private Button btnSave;
    private LostFoundDAO lostFoundDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        lostFoundDAO = new LostFoundDAO(this);
        lostFoundDAO.open();

        radioGroupPostType = findViewById(R.id.radioGroupPostType);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveItem());
    }

    private void saveItem() {
        // Getting all input variables
        int selectedPostTypeId = radioGroupPostType.getCheckedRadioButtonId();
        RadioButton selectedPostTypeRadioButton = findViewById(selectedPostTypeId);
        String postType = selectedPostTypeRadioButton.getText().toString();
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String description = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();
        String location = editTextLocation.getText().toString();


        // Create a new item object to insert into database
        long itemId = lostFoundDAO.insertItem(postType, name, phone, description, date, location);

        // Item is saved successfully toast is shown
        if (itemId != -1) {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Item Saved Successfully!", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
        // Item has Failed to save toast is shown
        else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Failed To Save Item!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        lostFoundDAO.close();
        super.onDestroy();
    }
}

