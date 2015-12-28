package com.rysiekblah.crom;

import android.net.Uri;

/**
 * Created by tomek on 7/8/14.
 */
public class ContractData {

    private Uri mUri;
    private String[] mProjection;
    private String mSelection;
    private String[] mSelectionArgs;
    private String mSortOrder;

    public ContractData(Uri mUri, String[] mProjection, String mSelection, String[] mSelectionArgs, String mSortOrder) {
        this.mUri = mUri;
        this.mProjection = mProjection;
        this.mSelection = mSelection;
        this.mSelectionArgs = mSelectionArgs;
        this.mSortOrder = mSortOrder;
    }

    public Uri getmUri() {
        return mUri;
    }

    public String[] getmProjection() {
        return mProjection;
    }

    public String getmSelection() {
        return mSelection;
    }

    public String[] getmSelectionArgs() {
        return mSelectionArgs;
    }

    public String getmSortOrder() {
        return mSortOrder;
    }
}
