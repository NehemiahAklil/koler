package com.chooloo.www.koler.ui.recentpreferences

import com.chooloo.www.koler.ui.base.BasePresenter

class RecentPreferencesPresenter<V : RecentPreferencesContract.View> : BasePresenter<V>(),
    RecentPreferencesContract.Presenter<V> {

    override fun onBlockNumberClick() {
        mvpView?.toggleNumberBlocked(true)
    }

    override fun onUnblockNumberClick() {
        mvpView?.toggleNumberBlocked(false)
    }
}