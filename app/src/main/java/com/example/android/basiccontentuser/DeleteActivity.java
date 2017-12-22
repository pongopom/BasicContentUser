package com.example.android.basiccontentuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    private String mId;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        TextView nameTextView = findViewById(R.id.name_text_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("ID");
        mName = extras.getString("NAME");
        nameTextView.setText(mName);
    }

    public void deleteName(View v) {
        //uncomment line below to clear all data
        // getContentResolver().delete(BasicContentContract.CONTENT_URI, "1",null);
        //comment line below when clearing all data
        getContentResolver().delete(BasicContentContract.CONTENT_URI,BasicContentContract.COLUMN_ID + "=?",new String[]{mId});
        Intent intent = new Intent();
        intent.putExtra("NAME", mName);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel (View v){
      finish();
    }



}
