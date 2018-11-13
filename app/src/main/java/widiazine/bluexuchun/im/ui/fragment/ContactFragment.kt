package widiazine.bluexuchun.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.adapter.ContactListAdapter
import widiazine.bluexuchun.im.adapter.EMContactListenerAdapter
import widiazine.bluexuchun.im.contract.ContactContract
import widiazine.bluexuchun.im.presenter.ContactPresenter
import widiazine.bluexuchun.im.ui.activity.AddFriendActivity
import widiazine.bluexuchun.im.widget.SlideBar

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
         *
         */
        addfriends.setOnClickListener {
            context?.startActivity<AddFriendActivity>()
        }
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
            adapter = ContactListAdapter(context,presenter.contactListItems,presenter)
        }

        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter(){
            override fun onContactAdded(username: String?) {
                presenter.loadContacts()
            }
        })

        slideBar.onSectionChangeListener = object :SlideBar.OnSectionChangeListener{
            override fun onSectionChange(firstLetter: String) {
                toggleLetter.text = firstLetter
                toggleLetter.visibility = View.VISIBLE
                recyclerview.smoothScrollToPosition(getPosition(firstLetter))
            }

            override fun onSlideFinish() {
                toggleLetter.visibility = View.GONE
            }

        }

        presenter.loadContacts()

    }

    /**
     * 根据字符 去查找位置
     * Arrays.binarySearch() 方法是java.util.Arrays包中的一种查找元素的方法。
     * 它使用的前提是数组是有序的。
     * minus 减去
     */
    private fun getPosition(firstLetter: String): Int =
        presenter.contactListItems.binarySearch {
            contactListItem ->  contactListItem.firstLetter.minus(firstLetter[0])
        }

}