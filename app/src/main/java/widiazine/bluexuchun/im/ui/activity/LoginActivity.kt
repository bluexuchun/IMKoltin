package widiazine.bluexuchun.im.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.R
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
        allTouch.setOnTouchListener { v, event ->
            isTouchView(event, listOf<View>(userName,passWord))
        }
        // 新用户 跳转到注册页
        newUser.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }

    fun login(){
        //隐藏软键盘
        hideKeyboard()
        if(hasWriteExternalStoragePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            val userNameString = userName.text.trim().toString()
            val passwordString = passWord.text.trim().toString()
            presenter.login(userNameString,passwordString)
        }else{
            applyWriteExternalStoragePermission(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //用户同意权限，用户开始登陆
            login()
        }else{
            toast("Permission Denied")
        }
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