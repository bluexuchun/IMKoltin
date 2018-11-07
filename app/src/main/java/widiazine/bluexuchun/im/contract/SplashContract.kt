package widiazine.bluexuchun.im.contract

interface SplashContract{

    interface Presenter:BasePresenter{
        // 检查登陆状态
        fun checkLoginStatus()
    }

    interface View{
        //没有登陆的ui处理
        fun onNotLoggedIn()

        //已经登陆的ui处理
        fun onLoggedIn()
    }
}