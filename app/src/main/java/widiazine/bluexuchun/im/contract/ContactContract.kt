package widiazine.bluexuchun.im.contract

interface ContactContract{
    interface Presenter:BasePresenter{
        // 加载联系人
        fun loadContacts()
    }

    interface View{
        //加载成功
        fun onLoadContactSuccess()
        //加载失败
        fun onLoadContactFailed()
    }
}