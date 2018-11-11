package widiazine.bluexuchun.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_contact_item.view.*
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.data.ContactListItem

/**
 * 列表内容的布局
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    /**
     * 就是给首字母赋值
     * 用户名字赋值
     * 头像赋值
     */
    fun bindView(contactListItem: ContactListItem) {
        /**
         * 这里的contactListItem.firstLetter
         * 是单条信息传入过来的时候里的值
         * 而firstLetter和userName是data(ContactListItem设置的) [ContactListItem(userName=xcwzy, firstLetter=X)]
         */
        if(contactListItem.showFirstLetter){
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = contactListItem.firstLetter.toString()
        }else{
            firstLetter.visibility = View.GONE
        }

        username.text = contactListItem.userName
    }

    init {
        // 加载这个view_contact_item这个布局
        View.inflate(context,R.layout.view_contact_item,this)
    }

}