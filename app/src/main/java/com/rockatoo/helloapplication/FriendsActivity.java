package com.rockatoo.helloapplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class FriendsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use default layout
        // setContentView(R.layout.activity_friends);

        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1};

        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        // Start Cursor Loader
        getLoaderManager().initLoader(0, null, (android.app.LoaderManager.LoaderCallbacks<Object>) this);
    }

    /*
     * Database Asynchronous Task
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projection = {ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME};
        String selection = "((" + ContactsContract.Data.DISPLAY_NAME + " not null) and (" +
                ContactsContract.Data.DISPLAY_NAME + " != ''))";

        // After Database Query, Return Result Cursor
        return new CursorLoader(this, ContactsContract.Data.CONTENT_URI, projection, selection, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        // If Cursor renew, Replace Adapter cursor into New data
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        // If Cursor reset, Adapter Cursor is initialized
        mAdapter.swapCursor(null);
    }
}
