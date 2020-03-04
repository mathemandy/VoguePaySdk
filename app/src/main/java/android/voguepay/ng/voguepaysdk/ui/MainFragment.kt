package android.voguepay.ng.voguepaysdk.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.voguepay.ng.voguepaysdk.R
import android.voguepay.ng.voguepaysdk.ui.payment.Constants
import android.voguepay.ng.voguepaysdk.WhiteLabel
import android.voguepay.ng.voguepaysdk.viewmodel.MainFragmentViewModel
import android.voguepay.ng.voguepaysdk2.Card
import android.voguepay.ng.voguepaysdk2.Payment
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject
import kotlin.experimental.and

class MainFragment : DaggerFragment(), View.OnClickListener {


    @set:Inject
    var viewModelFactory: ViewModelProvider.Factory? = null


    private val  mViewModel by lazy {
      ViewModelProviders.of(this, viewModelFactory).get(MainFragmentViewModel::class.java)
    }

    private lateinit var mPaymentButton: Button
    private lateinit var mPaymentButton2: Button

    private lateinit var mMerchantID: EditText
    private lateinit var mTotal: EditText
    private lateinit var mMemo: EditText
    private lateinit var mCur: EditText
    private lateinit var mError: TextView
    private lateinit var mProgressBar: ProgressBar
    var whiteLabel: WhiteLabel? = null


    private lateinit var mContext: Context

    var mListener: fragmentTransactionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_pay, container, false)
        this.mContext  = container!!.context

        setupView(v)

        return  v

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is fragmentTransactionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    private fun setupView(v : View) {
        mPaymentButton = v.findViewById(R.id.pay_button)
        mPaymentButton = v.findViewById(R.id.pay_button_seamless)
        mMerchantID = v.findViewById(R.id.merchant_id)
        mTotal = v.findViewById(R.id.total)
        mMemo = v.findViewById(R.id.memo)
        mCur = v.findViewById(R.id.currency)
        mError = v.findViewById(R.id.error)
        mProgressBar = v.findViewById(R.id.progress_bar)
        mPaymentButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val id = v?.id

        when(id){
            R.id.pay_button -> {

                mError.visibility = View.GONE
                mProgressBar.visibility = View.VISIBLE

                var payment = Payment()

                try {
                    payment.v_merchant_id = mMerchantID.text.toString()
                    payment.total = mTotal.text.toString()
                    payment.memo = mMemo.text.toString()
                    payment.cur = mCur.text.toString()

                }catch (e : Exception){

                }

                mViewModel.getURLForWebView(payment).observe(this, Observer {

                    if (it != null){
                        mProgressBar.visibility = View.GONE

                        when (it){
                            Constants.EMPTY_MERCHANT_ID -> {
                                mError.visibility = View.VISIBLE
                                mError.text = "Empty Merchant id"

                            }
                            Constants.INVALID_MERCHANT_ID -> {
                                mError.visibility = View.VISIBLE
                                mError.text = "Invalid Merchant id"
                            }
                            Constants.MEMO_IS_EMPTY -> {
                                mError.visibility = View.VISIBLE
                                mError.text = "Memo is Empty"
                            }
                            Constants.NO_RESULT ->{
                                mError.visibility = View.VISIBLE
                                mError.text = "No Result"
                            }
                            Constants.UNABLE_TO_PROCESS_COMMAND ->  {
                                mError.visibility = View.VISIBLE
                                mError.text = "Unable to Process Command"
                            }
                            else ->  {

                          mListener?.PaymentButtonClicked(it)
                            }
                        }

                    } else{
                        Log.e("MainE", "value is null")
                    }
                })


            }
            R.id.pay_button_seamless -> {
                whiteLabel = WhiteLabel()
                var  time = Calendar.getInstance().timeInMillis
                var mt = Random()

                whiteLabel!!.ref = time.toString() +  mt.nextInt(9999999).toString()
                var command_api_token = "3b75Qd2Jjqm8gm9pGBDB3xYhMD3wc"
                var merchant_email_on_voguepay = "vicformidable@gmail.com"
                hashStrings(command_api_token, whiteLabel!!.task, merchant_email_on_voguepay,
                    whiteLabel!!.ref
                )
                var card = Card()
                whiteLabel!!.card = card
                mViewModel.getURLForWebView(whiteLabel).observe(this, Observer {
                    if (it != null) {
                        mProgressBar.visibility = View.GONE
                        mError.text = it.message
                    }
                })
            }
        }
    }

    private fun hashStrings(commandApiToken : String, task : String?, merchant_email_on_vogue_pay : String?, time : String?) {
        var generatedHash : String = ""
        val stringToHash = commandApiToken + task + merchant_email_on_vogue_pay +  time
        Log.e("main", stringToHash)

        try {
            var md = MessageDigest.getInstance("SHA-512")
            var digest = md.digest(stringToHash.toByteArray())
            var sb =  StringBuilder()
            for (b in digest){
                sb.append(String.format("%02x", b and 0xff.toByte()))
            }
            Log.e("mainFragment", sb.toString())
            whiteLabel!!.hash = sb.toString()

        }catch (e : Exception){


        }
    }


    interface  fragmentTransactionListener{
        fun PaymentButtonClicked(responseUrl : String)
    }


}

