package com.example.android.basiccontentuser;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class QueryAllActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, QueryAllAdapter.ItemClickedListener {

    private QueryAllAdapter mQueryAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_all);

        //Set up the RecyclerView don't forget layoutManager and to set our custom adapter
        RecyclerView recyclerView = findViewById(R.id.query_all_recycler_view);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mQueryAllAdapter = new QueryAllAdapter(this, this);
        recyclerView.setAdapter(mQueryAllAdapter);

        //init the cursor loader for our back ground query
        getSupportLoaderManager().initLoader(1, null, this);
    }

    //--------------------------START OF CURSOR LOADER OVERRIDE METHODS-------------------------//
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //We should use id to make sure we have the right loader but we only have one in this case
        // This will be done on a background thread
        return new CursorLoader(this, BasicContentContract.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mQueryAllAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader arg0) {
        mQueryAllAdapter.swapCursor(null);
    }

//--------------------------END OF CURSOR LOADER OVERRIDE METHODS-------------------------//

    //RecyclerView action
    @Override
    public void itemClicked(Long id) {
        Intent intent = new Intent();
        intent.putExtra("ID", id);
        setResult(RESULT_OK, intent);
        finish();

    }
}
