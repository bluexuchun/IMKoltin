package widiazine.bluexuchun.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import widiazine.bluexuchun.im.data.AddFriendItem
import widiazine.bluexuchun.im.widget.AddFriendListItemView

class AddFriendListAdapter(
    val context: Context,
    val addFriendItems: MutableList<AddFriendItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int {
        return addFriendItems.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        /**
         * itemView 条目
         * AddFriendListItemView 是 ui样式
         * 意思为将每个条目 以AddFriendListItemView为主的样式
         * bindView 为绑定数据的意思 创建该方法并绑定数据
         */
        val addFriendListItem = p0.itemView as AddFriendListItemView
        addFriendListItem.bindView(addFriendItems[p1])
    }

    class AddFriendListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}