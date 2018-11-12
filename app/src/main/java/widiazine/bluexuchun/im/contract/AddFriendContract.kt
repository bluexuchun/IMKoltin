package widiazine.bluexuchun.im.contract

/**
 * mvp协议
 */
interface AddFriendContract{
    interface Presenter:BasePresenter{
        /**
         * 搜索逻辑
         */
        fun search(key: String)
    }

    interface View{
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}