package android.voguepay.ng.voguepaysdk2.view.card

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.voguepay.ng.voguepaysdk2.R
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment : Fragment(){
//    val mYViewModel : CardViewModel by viewModel()

    lateinit var amountEt: TextInputEditText
    lateinit var emailEt: TextInputEditText
    lateinit var cardNoTv: TextInputEditText
    lateinit var cardExpiryTv: TextInputEditText
    lateinit var cvvTv: TextInputEditText
    lateinit var amountTil: TextInputLayout
    lateinit var emailTil: TextInputLayout
    lateinit var cardNoTil: TextInputLayout
    lateinit var cardExpiryTil: TextInputLayout
    lateinit var cvvTil: TextInputLayout
    lateinit var saveCardSwitch: SwitchCompat
    lateinit var payButton: Button
    lateinit var savedCardBtn : Button
    private var progessDialog: ProgressDialog? = null
    private var pcidss_tv: TextView? = null
    lateinit var progressContainer: FrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

      val v = inflater.inflate(R.layout.fragment_card, container, false)
        initViews(v)
        return v
    }

    private fun initViews(v : View) {
        savedCardBtn = v.findViewById(R.id.vogue_savedCardButton) as Button
        amountEt = v.findViewById(R.id.vogue_amountTV)
        emailEt = v.findViewById(R.id.vogue_emailTv)
        cardNoTv = v.findViewById(R.id.vogue_cardNoTv)
        cardExpiryTv = v.findViewById(R.id.vogue_cardExpiryTv)
        cvvTv = v.findViewById(R.id.vogue_cvvTv)
        payButton = v.findViewById(R.id.vogue_payButton)
        saveCardSwitch = v.findViewById(R.id.vogue_saveCardSwitch)
        amountTil = v.findViewById(R.id.vogue_amountTil)
        emailTil = v.findViewById(R.id.vogue_emailTil)
        cardNoTil = v.findViewById(R.id.vogue_cardNoTil)
        cardExpiryTil = v.findViewById(R.id.vogue_cardExpiryTil)
        cvvTil = v.findViewById(R.id.vogue_cvvTil)
        pcidss_tv = v.findViewById(R.id.vogue_pcidss_compliant_tv) as TextView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mYViewModel.uiData.observe(this, Observer<PaymentUIModel>{
//            if (it != null){
//                Toast.makeText(context, "data not null", LENGTH_SHORT)
//                    .show()
//            }
//        })

//        mYViewModel.searchEvent.observe(this, Observer {
//            searchEvent ->
//        })

//        mYViewModel.getResponse()
    }


    fun getLink (){
//        mYViewModel.getPaymentLink(data = "")

    }

    fun testHisCode(){


    }

}