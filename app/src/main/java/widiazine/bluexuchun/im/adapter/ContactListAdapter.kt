package widiazine.bluexuchun.im.adapter

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.data.ContactListItem
import widiazine.bluexuchun.im.presenter.ContactPresenter
import widiazine.bluexuchun.im.ui.activity.ChatActivity
import widiazine.bluexuchun.im.widget.ContactListItemView

class ContactListAdapter(
    val context: Context,
    val contactListItems: MutableList<ContactListItem>,
    val presenter: ContactPresenter
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    /**
     * viewholder是用来初始化控件的
     * 同时需要理解ContactListItemView(context)
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    /**
     * 显示的列表数量
     */
    override fun getItemCount(): Int {
        return contactListItems.size
    }

    /**
     * 绑定viewholder
     * 即对列表的元素做操作
     */
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val contactListItemView = p0.itemView as ContactListItemView
        /**
         * 绑定数据 便于后续做操作
         * p1代表位置 也就是点击第几个item的内容
         */
        contactListItemView.bindView(contactListItems[p1])

        /**
         * 获取用户名
         * "username" to userName 就是传了一个username的参数 值为userName
         */
        val userName = contactListItems[p1].userName

        /**
         * 点击用户 进入与该用户的聊天页面
         */
        contactListItemView.setOnClickListener {
            context.startActivity<ChatActivity>("username" to userName)
        }

        /**
         * 长按用户 弹出是否删除好友
         * setOnLongClickListener 监听长按操作
         * AlertDialog.builder弹出对话框 context表示上下文
         * setTitle 对话框的标题
         * setMessage 对话框的内容
         * setNegativeButton 设置取消按钮 和 监听事件
         * setPositiveButton 设置确认阿牛 和 监听事件 删除好友的方法
         * 最后 show()显示
         * 然后给个true
         */
        contactListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_user),userName)
            AlertDialog.Builder(context)
                .setTitle("删除好友")
                .setMessage(message)
                .setNegativeButton("取消",null)
                .setPositiveButton("确认", DialogInterface.OnClickListener { dialog, which ->
                    deleteFriend(userName,p1)
                })
                .show()
            true
        }

    }

    private fun deleteFriend(userName: String,position:Int) {
        EMClient.getInstance().contactManager().aysncDeleteContact(userName,object:EMCallBackAdapter(){
            override fun onSuccess() {
                context.runOnUiThread {
                    toast("删除成功了")
                    /**
                     * 删除成功后 刷新列表
                     * 将fragment里的presenter方法传过来
                     */
                    presenter.loadContacts()
                }
            }

            override fun onError(code: Int, error: String?) {
                context.runOnUiThread {
                    toast("删除好友失败了,请重试")
                }
            }
        })
    }

    class ContactListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
