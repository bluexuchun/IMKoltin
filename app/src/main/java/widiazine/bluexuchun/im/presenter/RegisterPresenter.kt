package widiazine.bluexuchun.im.presenter

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
                    //开始注册
                    view.onStartRegister()

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




}