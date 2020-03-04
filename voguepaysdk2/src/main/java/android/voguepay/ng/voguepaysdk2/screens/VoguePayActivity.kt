package android.voguepay.ng.voguepaysdk2.screens

import android.os.Bundle
import android.view.View
import android.voguepay.ng.voguepaysdk2.*
import android.voguepay.ng.voguepaysdk2.commons.base.BaseActivity
import android.voguepay.ng.voguepaysdk2.databinding.ActivityVoguePayBinding
import android.voguepay.ng.voguepaysdk2.di.injectFeature
import android.voguepay.ng.voguepaysdk2.view.card.CardFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class VoguePayActivity : BaseActivity<ActivityVoguePayBinding, VoguePayViewModel>() {
    val TAG = LogHelper.makeLogTag(VoguePayActivity::class.java)

    var voguePayInitializer: VoguePayInitializer? = null
    var adapter: MainPageAdapter? = null
    var userTheme = 0

    val voguePayViewModel: VoguePayViewModel by viewModel()
    lateinit var binding: ActivityVoguePayBinding

    override fun getViewModel(): VoguePayViewModel = voguePayViewModel

    override fun getLayoutResourceId(): Int = R.layout.activity_vogue_pay

    override fun getBindingVariable(): Int = BR.ViewModel

    override fun getBinding(binding: ActivityVoguePayBinding) {
        this.binding = binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //we want to load our modules before we call super
        injectFeature()
        super.onCreate(savedInstanceState)
        getInitialiser()
        setupTheme()
        setupAdapter()

    }

    private fun setupTheme() {
        userTheme = voguePayInitializer?.theme ?: 0
        if (userTheme != 0) {
            setTheme(userTheme)

        }
    }

    private fun setupAdapter() {
        adapter = MainPageAdapter(supportFragmentManager)
        var voguePayFragment = arrayListOf<VoguePayFragment>()

        if (voguePayInitializer?.withCard == true) {
            voguePayFragment.add(
                VoguePayFragment(
                    CardFragment(),
                    "card"
                )
            )
        }


        adapter?.fragments = voguePayFragment
        binding.mainContent.pager.adapter = adapter
        binding.mainContent.tabLayout.setupWithViewPager(binding.mainContent.pager)

    }

    private fun getInitialiser() {
        try {
            voguePayInitializer = intent.getParcelableExtra(VoguePayConstants.VOGUEPAY_PARAMS)
        } catch (ignored: Exception) {
            LogHelper.e(TAG, "Error retrieving initialiser")
        }
    }

    override fun onBackPressed() {
        setResult(VoguePayConstants.REQUEST_CANCELLED)
        super.onBackPressed()
    }
}
