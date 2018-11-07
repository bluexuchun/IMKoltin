package widiazine.bluexuchun.im

import android.view.KeyEvent
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.contract.LoginContract
import widiazine.bluexuchun.im.presenter.LoginPresenter

class LoginActivity: BaseActivity(),LoginContract.View {

    val presenter = LoginPresenter(this)
    override fun init() {
        super.init()
        bt_login.setOnClickListener {
            login()
        }
        passWord.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }

    fun login(){
        val userNameString = userName.text.trim().toString()
        val passwordString = passWord.text.trim().toString()
        presenter.login(userNameString,passwordString)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        passWord.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        // 弹出一个进度条
        showProgress(getString(R.string.logining))
    }

    override fun onLoggedInSuccess() {
        //隐藏进度条
        dismissProgress()
        //进入到主界面
        startActivity<MainActivity>()
        //退出LoginActivity
        finish()
    }

    override fun onLoggedFailed() {
        //隐藏进度条
        dismissProgress()
        //弹出toast 给个提示
        toast(getString(R.string.login_failed))
    }

    override fun getLayoutResId(): Int {
         return R.layout.activity_login
    }

}