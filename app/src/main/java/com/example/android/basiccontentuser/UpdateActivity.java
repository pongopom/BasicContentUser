package com.example.android.basiccontentuser;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText mEditName;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mEditName = findViewById(R.id.edit_text_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("ID");
        String name = extras.getString("NAME");
        mEditName.setText(name);
    }

    public void editName(View v) {
        ContentValues contentValues = new ContentValues();
        String newName = mEditName.getText().toString();
        if (newName.equals("")) {
            Toast.makeText(this, "Enter a valid name in the name field",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        contentValues.put(BasicContentContract.COLUMN_TITLE, newName);
        getContentResolver().update(BasicContentContract.CONTENT_URI, contentValues, BasicContentContract.COLUMN_ID + "=?",new String[]{mId});
        Intent intent = new Intent();
        intent.putExtra("NAME", newName);
       setResult(RESULT_OK, intent);
        finish();
    }

}
