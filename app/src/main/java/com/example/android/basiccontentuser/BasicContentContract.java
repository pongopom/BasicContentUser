package com.example.android.basiccontentuser;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by peterpomlett on 15/12/2017.
 */

public class BasicContentContract implements BaseColumns {

    public static final String TABLE_NAME = "nameTable";
    public static final String COLUMN_TITLE = "name";
    public static final String COLUMN_ID = "_id";

    public static final String AUTHORITY = "com.example.android.basiccontentprovider";
    public static final Uri BASE_CONTENT_URI = Uri.parse( "content://" + AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

}
