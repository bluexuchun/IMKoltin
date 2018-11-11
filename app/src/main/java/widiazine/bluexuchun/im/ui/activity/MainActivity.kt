package widiazine.bluexuchun.im.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.factory.FragmentFactory

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()

        /**
         * bottomBar tab的切换事件
         * 需要创建工厂模式 factory
         * 引出获取fragment的实例
         * 将点击的tabid 传给FragmentFactory工厂模式 返回实例id 并将当前页面替换为返回实例的页面
         */

        bottomBar.setOnTabSelectListener { tabId ->
            // 获取fragment管理器
            val beginTransaction = supportFragmentManager.beginTransaction()
            // 将点击的fragment页面 显示到当前屏幕
            beginTransaction.replace(R.id.fragmentframe, FragmentFactory.instance.getFragment(tabId)!!)
            // 提交
            beginTransaction.commit()
        }
    }

}
