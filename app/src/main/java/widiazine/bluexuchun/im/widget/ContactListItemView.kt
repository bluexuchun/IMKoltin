package widiazine.bluexuchun.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import widiazine.bluexuchun.im.R

/**
 * 统一内容的布局
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        // 加载这个view_contact_item这个布局
        View.inflate(context,R.layout.view_contact_item,this)
    }

}