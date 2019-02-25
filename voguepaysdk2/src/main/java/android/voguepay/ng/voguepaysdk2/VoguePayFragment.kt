package android.voguepay.ng.voguepaysdk2

import androidx.fragment.app.Fragment

class VoguePayFragment(fragments : Fragment, title: String){

    var fragment : Fragment
    internal  set
    var title: String
    internal set

    init {
        this.title = title
        this.fragment = fragments
    }

}
