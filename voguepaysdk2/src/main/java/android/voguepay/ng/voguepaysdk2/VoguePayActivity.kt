package android.voguepay.ng.voguepaysdk2

import android.os.Bundle
import android.voguepay.ng.voguepaysdk2.view.card.CardFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class VoguePayActivity : AppCompatActivity () {
    val TAG = LogHelper.makeLogTag(VoguePayActivity::class.java)

    var voguePayInitializer : VoguePayInitializer? = null
    var adapter : MainPageAdapter? = null
    lateinit  var pager : ViewPager
    lateinit var  tabLayout : TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            voguePayInitializer = intent.getParcelableExtra(VoguePayConstants.VOGUEPAY_PARAMS)


        }catch (ignored : Exception){
            LogHelper.e(TAG, "Error retrieving initialiser")
        }

        setContentView(R.layout.activity_vogue_pay)

        pager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabLayout)

        adapter  = MainPageAdapter(supportFragmentManager)
        var voguePayFragment = arrayListOf<VoguePayFragment>()

        if (voguePayInitializer?.withCard == true){
            voguePayFragment.add(VoguePayFragment(CardFragment(), "card"))
        }

        adapter?.fragments  = voguePayFragment
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)

    }


    override fun onBackPressed() {
        setResult(VoguePayConstants.REQUEST_CANCELLED)
        super.onBackPressed()
    }
}
