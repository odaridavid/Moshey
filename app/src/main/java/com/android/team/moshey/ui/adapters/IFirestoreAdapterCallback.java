package com.android.team.moshey.ui.adapters;

/**
 * Created By blackcoder
 * On 04/04/19
 **/
public interface IFirestoreAdapterCallback {
    /**
     * Facilitates UI Changes incase data state changes in adapter
     *
     * @param itemCount no of items received from firestore
     */
    void onAdapterDataChanged(int itemCount);
}
