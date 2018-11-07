package widiazine.bluexuchun.im

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(),null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    open fun init(){
        //初始化一些公共的功能，子类也可以复写该方法，完成自己的初始化
    }

    abstract fun getLayoutResId():Int


}