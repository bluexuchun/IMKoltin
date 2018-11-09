package widiazine.bluexuchun.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.adapter.ContactListAdapter
import widiazine.bluexuchun.im.contract.ContactContract
import widiazine.bluexuchun.im.presenter.ContactPresenter

class ContactFragment:BaseFragment(),ContactContract.View{

    val presenter = ContactPresenter(this)

    override fun onLoadContactSuccess() {
        swiperrefresh.isRefreshing = false
        recyclerview.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFailed() {
        swiperrefresh.isRefreshing = false
        context?.toast("加载失败")
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_contact
    }

    override fun init() {
        super.init()
        headerTitle.text = "联系人"
        addfriends.visibility = View.VISIBLE

        swiperrefresh.apply {
            setColorSchemeResources(R.color.colorPrimary)
            // 监听手动刷新 也要去加载数据
            setOnRefreshListener {
                presenter.loadContacts()
            }
        }

        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }

        presenter.loadContacts()

    }

}