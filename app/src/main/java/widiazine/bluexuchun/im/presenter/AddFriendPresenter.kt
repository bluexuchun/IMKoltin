package widiazine.bluexuchun.im.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import widiazine.bluexuchun.im.contract.AddFriendContract
import widiazine.bluexuchun.im.data.AddFriendItem
import widiazine.bluexuchun.im.data.db.IMDatabase

class AddFriendPresenter(val view:AddFriendContract.View):AddFriendContract.Presenter{

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {
        addFriendItems.clear()
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                println(p0)
                if(p1 == null){
                    /**
                     * 处理数据
                     * 创建AddFrientItem的集合
                     */
                    val allContacts = IMDatabase.instance.getAllContacts()
                    println(allContacts)
                    doAsync {
                        p0?.forEach {
                            // 比对 是否已经添加过好友
                            var isAdded = false
                            for (contact in allContacts){
                                if(contact.name == it.username){
                                    isAdded = true
                                }
                            }

                            val addFriendItem = AddFriendItem(it.username,it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)


                        }
                        uiThread {
                            view.onSearchSuccess()
                        }
                    }
                }else{
                    view.onSearchFailed()
                }
            }
        })
    }

}