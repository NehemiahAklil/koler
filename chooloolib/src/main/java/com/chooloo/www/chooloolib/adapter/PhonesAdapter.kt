package com.chooloo.www.chooloolib.adapter

import android.graphics.Color
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.ViewGroup
import com.chooloo.www.chooloolib.R
import com.chooloo.www.chooloolib.model.ListData
import com.chooloo.www.chooloolib.model.PhoneAccount
import com.chooloo.www.chooloolib.interactor.animation.AnimationsInteractor
import com.chooloo.www.chooloolib.interactor.string.StringsInteractor
import com.chooloo.www.chooloolib.ui.widgets.listitem.ListItem
import com.chooloo.www.chooloolib.ui.widgets.listitem.ListItemButton
import com.chooloo.www.chooloolib.ui.widgets.listitem.ListItemHolder
import javax.inject.Inject
import javax.inject.Singleton

class PhonesAdapter @Inject constructor(
    animationsInteractor: AnimationsInteractor,
    private val stringsInteractor: StringsInteractor
) : ListAdapter<PhoneAccount>(animationsInteractor) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListItemHolder(ListItemButton(parent.context))

    override fun onBindListItem(listItem: ListItem, item: PhoneAccount) {
        listItem.apply {
            isPadded = false
            titleText = item.number
            captionText = stringsInteractor.getString(Phone.getTypeLabelResource(item.type))

            setTitleBold(true)
            setImageBackgroundColor(Color.TRANSPARENT)
            setImageResource(R.drawable.ic_call_black_24dp)
        }
    }

    override fun convertDataToListData(data: List<PhoneAccount>) = ListData.fromPhones(data)
}