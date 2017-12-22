package com.example.android.basiccontentuser;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by peterpomlett on 18/12/2017.
 */

public class QueryAllAdapter extends RecyclerView.Adapter<QueryAllAdapter.QueryAllViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    private final ItemClickedListener mItemClickedListener;

    public interface ItemClickedListener {
        void itemClicked(Long id);
    }

    public QueryAllAdapter(ItemClickedListener itemClickedListener, Context context) {
        this.mItemClickedListener = itemClickedListener;
        this.mContext = context;
    }

    @Override
    public QueryAllAdapter.QueryAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.query_all_recycler_item, parent, false);
        return new QueryAllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QueryAllAdapter.QueryAllViewHolder holder, int position) {
        mCursor.moveToPosition(position); // get to the right location in the cursor
        int indexName = mCursor.getColumnIndex(BasicContentContract.COLUMN_TITLE);
        int indexId = mCursor.getColumnIndex(BasicContentContract.COLUMN_ID);
        String title = mCursor.getString(indexName);
        final long id = mCursor.getLong(indexId);
        holder.mNameTextView.setText(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mItemClickedListener.itemClicked(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();

    }


    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }


    public class QueryAllViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;

        public QueryAllViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.item_textView);

        }
    }


}
