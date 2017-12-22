package com.example.android.basiccontentuser;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
EditText mNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mNameTextView = findViewById(R.id.create_text_view);
    }

    public void addName(View v){
        ContentValues contentValues = new ContentValues();
        String name = mNameTextView.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Enter a valid name in the name field",
                    Toast.LENGTH_SHORT).show();
            return;
        }
       contentValues.put(BasicContentContract.COLUMN_TITLE, name);
        getContentResolver().insert(BasicContentContract.CONTENT_URI, contentValues);
        Intent intent = new Intent();
        intent.putExtra("NAME", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
