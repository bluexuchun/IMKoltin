package widiazine.bluexuchun.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import widiazine.bluexuchun.im.contract.ContactContract
import widiazine.bluexuchun.im.data.ContactListItem

class ContactPresenter(val view:ContactContract.View):ContactContract.Presenter{

    /**
     * 初始化列表 这里为什么要用mutableListOf<>() 就是因为mutableListOf是一个可变列表
     * 添加数据 用add
     */
    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {

        /**
         * 获取所有好友列表
         * 这里就是属于异步获取数据
         * 主线程的Ui 显示刷新的progress
         * 子线程在获取好友列表数据
         * 获取成功后 切换主线程 告诉主线程我拿到数据了 并显示加载成功 并执行iew.onLoadContactSuccess()
         */
        doAsync {
            try {
                contactListItems.clear()
                /**
                 * 获取所有好友列表
                 */
                val users = EMClient.getInstance().contactManager().allContactsFromServer

                /**
                 * 根据首字母排序
                 */

                users.sortBy { it[0] }
                users.forEachIndexed { index, s ->
                    /**
                     * 判断是否显示首字符
                     *
                     */
                    val showFirstLetter = index == 0 || s[0] != users[index - 1][0]
                    /**
                     * it 代表遍历的值 -》 用户名
                     */
                    val contactListItem = ContactListItem(s,s[0].toUpperCase(),showFirstLetter)
                    contactListItems.add(contactListItem)
                }
                uiThread {
                    view.onLoadContactSuccess()
                }
            } catch (e:HyphenateException){
                uiThread {
                    view.onLoadContactFailed()
                }
            }
        }

    }

}