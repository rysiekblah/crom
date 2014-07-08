package com.rysiekblah.crom;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by tomek on 7/8/14.
 * TODO: impement overwritten methods - TBD
 */
public class CromLoader<T> extends AsyncTaskLoader<List<T>> {

    private ContractData mContractData;
    private Class<T> mClazz;
    private Context mContext;

    public CromLoader(Context context, ContractData contractData, Class<T> clazz) {
        super(context);
        mContext = context;
        mContractData = contractData;
        mClazz = clazz;
    }

    @Override
    public List<T> loadInBackground() {
        Crom crom = new Crom();
        Cursor cursor = mContext.getContentResolver().query(
                mContractData.getmUri(), mContractData.getmProjection(), mContractData.getmSelection(),
                mContractData.getmSelectionArgs(), mContractData.getmSortOrder());

        return crom.cursorToPojoList(cursor, mClazz);
    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     * TODO: TBD
     */
    @Override
    public void deliverResult(List<T> data) {
        super.deliverResult(data);
    }

    /**
     * Handles a request to start the Loader.
     * TODO: TBD
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    /**
     * Handles a request to stop the Loader.
     * TODO: TBD
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    /**
     * Handles a request to cancel a load.
     * TODO: TBD
     */
    @Override
    public void onCanceled(List<T> data) {
        super.onCanceled(data);
    }

    /**
     * Handles a request to completely reset the Loader.
     * TODO: TBD
     */
    @Override
    protected void onReset() {
        super.onReset();
    }
}
