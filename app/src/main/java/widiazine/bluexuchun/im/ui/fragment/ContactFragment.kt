package widiazine.bluexuchun.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.adapter.ContactListAdapter
import widiazine.bluexuchun.im.adapter.EMContactListenerAdapter
import widiazine.bluexuchun.im.contract.ContactContract
import widiazine.bluexuchun.im.presenter.ContactPresenter

class ContactFragment:BaseFragment(),ContactContract.View{

    val presenter = ContactPresenter(this)

    /**
     * 加载数据成功
     * swiperrefresh.isRefreshing 是 SwipeRefreshLayout的刷新状态
     * recyclerview.adapter?.notifyDataSetChanged()
     * 代表 去通知列表刷新数据
     */
    override fun onLoadContactSuccess() {
        swiperrefresh.isRefreshing = false
        recyclerview.adapter?.notifyDataSetChanged()
    }

    /**
     * 加载数据失败
     */
    override fun onLoadContactFailed() {
        swiperrefresh.isRefreshing = false
        context?.toast("加载失败")
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_contact
    }

    /**
     * 初始化
     */
    override fun init() {
        super.init()
        /**
         * 设置公共header title的文字为联系人
         */
        headerTitle.text = "联系人"
        /**
         * 把公共header的增加朋友的按钮显示出来
         * View.VISIBLE很重要
         */
        addfriends.visibility = View.VISIBLE
        /**
         * swiperrefresh.apply可以在里面应用所有的 方便
         * setColorSchemeResources 设置刷新的颜色
         * setOnRefreshListener{presenter.loadContacts}
         * 监听手动上拉刷新 加载列表数据
         */
        swiperrefresh.apply {
            setColorSchemeResources(R.color.colorPrimary)
            // 监听手动刷新 也要去加载数据
            setOnRefreshListener {
                presenter.loadContacts()
            }
        }
        /**
         * recyclerview.apply
         * setHasFixedSize 自动去设置列表的大小
         * layoutManager 是代表recyclerview的样式 横向，纵向，各种花式布局
         * adapter 初始化适配器
         * ContactListAdapert 方法 需要好好理解 暂时还不是很透
         */
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context,presenter.contactListItems)
        }

        /**
         * 监听删除联系人后刷新列表
         */
        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter() {
            override fun onContactDeleted(username: String?) {
                // 重新获取联系人数据
                presenter.loadContacts()
            }

        })

        presenter.loadContacts()

    }

}