package widiazine.bluexuchun.im.contract

interface RegisterContract{
    interface Presenter:BasePresenter{
        // 注册的方法
        fun register(username:String,password:String,confrimPassword:String)
    }

    interface View{
        // 开始注册
        fun onStartRegister()
        // 登陆成功
        fun onRegisterSuccess()
        // 登陆失败
        fun onRegisterError()
        // 用户名不合法
        fun onUserNameError()
        // 密码不合法
        fun onPassWordError()
        // 两次密码不符合
        fun onRePassWordErrror()
    }
}