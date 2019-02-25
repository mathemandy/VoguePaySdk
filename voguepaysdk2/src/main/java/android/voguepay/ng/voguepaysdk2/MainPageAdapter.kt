package android.voguepay.ng.voguepaysdk2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fragments: List<VoguePayFragment> = arrayListOf()


    // Return a different fragment for position based on additional state tracked in a member variable

    override fun getItem(position: Int): Fragment {
        return fragments[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragments[position].title
    }

    override fun getCount(): Int {
        return fragments.size
    }




}