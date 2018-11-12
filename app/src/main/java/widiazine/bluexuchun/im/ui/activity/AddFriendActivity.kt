package widiazine.bluexuchun.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_addfriend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.adapter.AddFriendListAdapter
import widiazine.bluexuchun.im.contract.AddFriendContract
import widiazine.bluexuchun.im.presenter.AddFriendPresenter

class AddFriendActivity:BaseActivity(),AddFriendContract.View{

    val presenter = AddFriendPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.activity_addfriend
    }

    override fun init() {
        super.init()
        headerTitle.text = "添加好友"

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.addFriendItems)
        }

        /**
         * 搜索按钮
         */
        search.setOnClickListener {
            search()
        }
        userName.setOnEditorActionListener { v, actionId, event ->
            search()
            true
        }

    }

    fun search(){
        /**
         * 隐藏软键盘
         */
        hideKeyboard()
        showProgress("搜索中...")
        val key = userName.text.trim().toString()
        presenter.search(key)
    }


    override fun onSearchSuccess() {
        dismissProgress()
        toast("搜索成功...")
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        toast("搜索失败...")
    }

}