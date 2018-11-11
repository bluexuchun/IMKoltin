package widiazine.bluexuchun.im.factory

import android.support.v4.app.Fragment
import widiazine.bluexuchun.im.R
import widiazine.bluexuchun.im.ui.fragment.ContactFragment
import widiazine.bluexuchun.im.ui.fragment.ConversationFragment
import widiazine.bluexuchun.im.ui.fragment.DynamicFragment

class FragmentFactory private constructor(){

    /**
     * 创建conversation contact dynamic的fragment的实例id
     */
    val conversation by lazy{
        ConversationFragment()
    }

    val contact by lazy{
        ContactFragment()
    }

    val dynamic by lazy {
        DynamicFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }

    //返回实例id
    fun getFragment(tabId:Int): Fragment?{
        when(tabId){
            R.id.conversation -> return conversation
            R.id.contact -> return contact
            R.id.dynamic -> return dynamic
        }
        return null
    }
}