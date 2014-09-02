package com.rysiekblah.crom;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

/**
 * Created by tomek on 7/8/14.
 */
public class CromLoader<T> extends AbstractLoader<List<T>> {

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

    @Override
    protected void onReleaseResources(List<T> data) {

    }
}
