package widiazine.bluexuchun.im.ui.fragment

import android.view.View
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.im.R

class ContactFragment:BaseFragment(){
    override fun getLayoutResId(): Int {
        return R.layout.fragment_contact
    }

    override fun init() {
        super.init()
        headerTitle.text = "联系人"
        addfriends.visibility = View.VISIBLE
        swiperrefresh.apply {
            setColorSchemeResources(R.color.colorPrimary)
        }

    }

}