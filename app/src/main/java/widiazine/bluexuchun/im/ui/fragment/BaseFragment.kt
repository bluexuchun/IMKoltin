package widiazine.bluexuchun.im.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment: Fragment(){
    /**
     * 与Activity一样 onCreateView
     * inflater.inflate的解释
     * 从一个Context中，获得一个布局填充器，这样你就可以使用这个填充器来把xml布局文件转为View对象了。
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(),null)
    }

    /**
     * View对象创建完成
     * 做一些初始化操作
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    open fun init(){
        //初始化一些公共的功能，子类也可以复写该方法，完成自己的初始化
    }

    abstract fun getLayoutResId():Int


}