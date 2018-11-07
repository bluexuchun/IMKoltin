package widiazine.bluexuchun.im.contract

interface LoginContract {

    interface Presenter: BasePresenter{

        fun login(userName:String,passWord:String)

    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedFailed()
    }

}