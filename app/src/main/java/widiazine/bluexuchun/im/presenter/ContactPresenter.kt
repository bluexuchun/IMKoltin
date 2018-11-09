package widiazine.bluexuchun.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import widiazine.bluexuchun.im.contract.ContactContract

class ContactPresenter(val view:ContactContract.View):ContactContract.Presenter{
    override fun loadContacts() {
        //放到子线程去做 异步处理
        doAsync {
            try {
                val users = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread {
                    view.onLoadContactSuccess()
                }
            } catch (e:HyphenateException){
                uiThread {
                    view.onLoadContactFailed()
                }
            }
        }

    }

}