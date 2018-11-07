package widiazine.bluexuchun.im

import android.os.Handler
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import widiazine.bluexuchun.im.contract.SplashContract
import widiazine.bluexuchun.im.presenter.SplashPresenter

class SplashActivity:BaseActivity(),SplashContract.View{

    val presenter = SplashPresenter(this)

    //Kotlin语言中使用"companion object"修饰静态方法，可以使用类名.方法名的形式调用
    companion object {
        val DELAY = 2000L
    }

    // 延迟方法
    val handler by lazy{
        Handler()
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    //已经登陆 延时2s,跳转到登陆界面
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }

    //还没有登陆 跳往登录页
    override fun onNotLoggedIn() {
        toast("还没登陆")
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY)
    }
}