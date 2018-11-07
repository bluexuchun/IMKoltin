package widiazine.bluexuchun.im

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

abstract class BaseActivity:AppCompatActivity(){

    val progressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    //子类必须实现该方法 返回一个布局资源的id
    abstract fun getLayoutResId():Int

    open fun init(){
        //初始化一些公共的功能，比如说摇一摇，子类可以复写该方法，完成自己的初始化
    }

    fun showProgress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgress(){
        progressDialog.dismiss()
    }

    //隐藏软键盘
    fun hideKeyboard(view: View){
        var imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(imm != null){
            imm.hideSoftInputFromWindow(view.getWindowToken(),0)
        }
    }

}