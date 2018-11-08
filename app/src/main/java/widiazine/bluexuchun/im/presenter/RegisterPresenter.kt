package widiazine.bluexuchun.im.presenter

import android.view.View
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import widiazine.bluexuchun.im.contract.BasePresenter
import widiazine.bluexuchun.im.contract.RegisterContract
import widiazine.bluexuchun.im.extentions.isValidPassword
import widiazine.bluexuchun.im.extentions.isValidUserName

class RegisterPresenter(val view:RegisterContract.View):BasePresenter,RegisterContract.Presenter{
    // 注册方法
    override fun register(username: String, password: String, confrimPassword: String) {
        if(username.isValidUserName()){
            if(password.isValidPassword()){
                if(password.equals(confrimPassword)){
                    //密码和确认密码一致
                    view.onStartRegister()
                    //开始注册
                    registerUser(username,password)
                }else{
                    view.onRePassWordErrror()
                }
            }else{
                view.onPassWordError()
            }
        }else{
            view.onUserNameError()
        }
    }

    private fun registerUser(username: String,password: String) {
        var bu = BmobUser()
        bu.username = username
        bu.setPassword(password)
        bu.signUp<Bmob>(object : SaveListener<Bmob>(){
            override fun done(p0: Bmob?, p1: BmobException?) {
                if(p1 == null){
                    //抛出异常等于Null 说明注册成功

                    //注册到环信
                    registerToEasyMob(username,password)
                }else{
                    //注册失败
                    if(p1.errorCode == 202){
                        view.onUserExist()
                    }else{
                        view.onRegisterError()
                    }
                }
            }
        })
    }

    private fun registerToEasyMob(username: String, password: String) {
        //线程切换
        // 网络请求放在子线程中执行 所以 需要通知主线程
        doAsync {
            // doAsync 放在子线程执行
            try {
                //注册失败会抛出HyphenateException
                EMClient.getInstance().createAccount(username,password)
                //注册成功
                uiThread {
                    view.onRegisterSuccess()
                }
            } catch (e:HyphenateException){
                //注册失败
                uiThread {
                    view.onRegisterError()
                }

            }

        }

    }


}