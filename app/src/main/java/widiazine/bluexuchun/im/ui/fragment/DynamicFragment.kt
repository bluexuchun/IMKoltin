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
        /**
         * 设置公共header的文字
         */
        headerTitle.text = "动态"
        /**
         * 这里有一个知识点
         * %s 为占位符 必须是在strings.xml中设置才有效
         * 可以通过 String.format(昵称:%s,"徐淳"))
         * 结果为 昵称:徐淳 可以将占位符赋值
         */
        val nickname = String.format(getString(R.string.getUsername),EMClient.getInstance().currentUser)
        username.text = nickname

        logout.setOnClickListener {
            logoutFun()
        }
    }

    /**
     * 注销
     * 注意一点
     * 一般操作中 如果是同步的请求数据 需要做异步处理
     * 异步处理：放到子线程去运行
     * 结果出来后 通过切换线程 去通知主线程
     */
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
