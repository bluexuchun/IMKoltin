package widiazine.bluexuchun.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.adapter.EMCallBackAdapter
import widiazine.bluexuchun.im.data.AddFriendItem

class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){
    fun bindView(addFriendItem: AddFriendItem) {
        if(addFriendItem.isAdded){
            add.isEnabled = false
            add.text = "已添加"
        }else{
            add.isEnabled = true
            add.text = "添加"
        }
        username.text = addFriendItem.userName
        timestamp.text = addFriendItem.timestamp

        add.setOnClickListener {
            addFriend(addFriendItem.userName)
        }
    }

    private fun addFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncAddContact(userName,null,object :EMCallBackAdapter(){
            override fun onSuccess() {
                context.runOnUiThread {
                    toast("发送请求成功")

                }
            }

            override fun onError(code: Int, error: String?) {
                context.runOnUiThread {
                    toast("发送请求失败")
                }
            }
        })
    }

    init {
        /**
         * 创建view_add_friend_item
         */
        View.inflate(context, R.layout.view_add_friend_item, this)
    }
}