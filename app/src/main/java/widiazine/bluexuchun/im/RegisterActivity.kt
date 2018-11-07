package widiazine.bluexuchun.im

import android.view.KeyEvent
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.contract.RegisterContract
import widiazine.bluexuchun.im.presenter.RegisterPresenter

class RegisterActivity:BaseActivity(),RegisterContract.View{

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        bt_register.setOnClickListener {
            register()
        }
        rePassWord.setOnEditorActionListener(object :TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                register()
                return true
            }
        })
    }

    private fun register() {
        // 隐藏软键盘
        hideKeyboard(this)
        var username = userName.text.trim().toString()
        var password = passWord.text.trim().toString()
        var repassword = rePassWord.text.trim().toString()
        presenter.register(username,password,repassword)
    }

    override fun onStartRegister() {
        showProgress("正在注册")
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        finish()
    }

    override fun onRegisterError() {
        dismissProgress()
        toast("注册失败")
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPassWordError() {
        passWord.error = getString(R.string.password_error)
    }

    override fun onRePassWordErrror() {
        rePassWord.error = "两次密码不一致"
    }

    override fun getLayoutResId(): Int = R.layout.activity_register



}