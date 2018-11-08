package widiazine.bluexuchun.im.ui.fragment

import android.content.Context
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.adapter.EMCallBackAdapter
import widiazine.bluexuchun.im.ui.activity.LoginActivity

class DynamicFragment:BaseFragment(){
    override fun getLayoutResId(): Int {
        return R.layout.fragment_dynamic
    }

    override fun init() {
        super.init()
        headerTitle.text = "动态"
        val nickname = String.format(getString(R.string.getUsername),EMClient.getInstance().currentUser)
        username.text = nickname

        logout.setOnClickListener {
            logoutFun()
        }
    }

    private fun logoutFun() {
        EMClient.getInstance().logout(true,object: EMCallBackAdapter() {
            override fun onSuccess() {
                context?.runOnUiThread {
                    toast("注销成功")
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onError(code: Int, error: String?) {
                //切换到主线程
                context?.runOnUiThread {
                    toast("注销失败")
                }
            }

        })
    }

}
