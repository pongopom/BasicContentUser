package com.example.android.basiccontentuser;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mNameTextView;
    private String mName = "";
    private String mId = "0";
    Button mUpdateButton;
    Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameTextView = findViewById(R.id.selected_textView);
        mUpdateButton = findViewById(R.id.update_button);
        mDeleteButton = findViewById(R.id.delete_button);
        mUpdateButton.setEnabled(false);
        mDeleteButton.setEnabled(false);
    }

    // CRUD buttons actions
    public void createTapped(View v) {
        Intent createIntent = new Intent(this, InsertActivity.class);
        startActivityForResult(createIntent, 1);
    }

    public void readTapped(View v) {
        Intent queryAllIntent = new Intent(this, QueryAllActivity.class);
        startActivityForResult(queryAllIntent, 2);
    }

    public void updateTapped(View v) {
        Intent updateIntent = new Intent(this, UpdateActivity.class);
        Bundle extras = new Bundle();
        extras.putString("ID", mId);
        extras.putString("NAME", mName);
        updateIntent.putExtras(extras);
        startActivityForResult(updateIntent, 3);
    }

    public void deleteTapped(View v) {
        Intent deleteIntent = new Intent(this, DeleteActivity.class);
        Bundle extras = new Bundle();
        extras.putString("ID", mId);
        extras.putString("NAME", mName);
        deleteIntent.putExtras(extras);
        startActivityForResult(deleteIntent, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check that it is the SecondActivity with an OK result
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("NAME");
                Toast.makeText(this, name + " has been added",
                        Toast.LENGTH_SHORT).show();
                clearUi();
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Long i = data.getLongExtra("ID", 0);
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(BasicContentContract.CONTENT_URI,
                        null, "_id=?", new String[]{String.valueOf(i)}, null);
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(BasicContentContract.COLUMN_TITLE);
                String title = cursor.getString(index);
                mName = title;
                mId = String.valueOf(i);
                System.out.println(title);
                mNameTextView.setText(title);
                mUpdateButton.setEnabled(true);
                mDeleteButton.setEnabled(true);
                cursor.close();
            }
        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("NAME");
                Toast.makeText(this, "Selected name has been changed to" + name,
                        Toast.LENGTH_SHORT).show();
                clearUi();
            }
        }
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("NAME");
                Toast.makeText(this, name + " has been deleted",
                        Toast.LENGTH_SHORT).show();
                clearUi();
            }
        }
    }

    private void clearUi() {
        mId = "0";
        mName = "";
        mNameTextView.setText("");
mUpdateButton.setEnabled(false);
mDeleteButton.setEnabled(false);
    }

}
