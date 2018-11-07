package widiazine.bluexuchun.im.presenter

import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import widiazine.bluexuchun.im.adapter.EMCallBackAdapter
import widiazine.bluexuchun.im.contract.LoginContract
import widiazine.bluexuchun.im.extentions.isValidPassword
import widiazine.bluexuchun.im.extentions.isValidUserName

class LoginPresenter(val view:LoginContract.View):LoginContract.Presenter{

    override fun login(userName: String, passWord: String) {
        if(userName.isValidUserName()){
            //用户名合法，继续校验密码
            if(passWord.isValidPassword()){
                view.onStartLogin()
                loginEaseMob(userName,passWord) //登陆到环信
            }else{
                view.onPasswordError()
            }
        }else{
            view.onUserNameError()
        }
    }

    //登陆环信的方法
    private fun loginEaseMob(userName: String, passWord: String) {
        EMClient.getInstance().login(userName,passWord,object : EMCallBackAdapter() {
            // 环信的结果在子线程回调 所以需要在主线程通知view层数据改变了
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                // 在主线程通知View层
                uiThread {
                    view.onLoggedInSuccess()
                }
            }

            override fun onError(code: Int, error: String?) {
                uiThread {
                    view.onLoggedFailed()
                }
            }
        })
    }
}