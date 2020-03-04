package android.voguepay.ng.voguepaysdk2.view.card

import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import android.view.View.GONE
import android.voguepay.ng.voguepaysdk2.*
import android.voguepay.ng.voguepaysdk2.commons.BaseFragment
import android.voguepay.ng.voguepaysdk2.databinding.FragmentCardBinding
import android.voguepay.ng.voguepaysdk2.screens.VoguePayActivity
import android.voguepay.ng.voguepaysdk2.util.ExpiryWatcher
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.regex.Pattern

class CardFragment : BaseFragment<FragmentCardBinding, CardViewModel>(), View.OnClickListener {


    lateinit var binding: FragmentCardBinding

    override fun getLayoutId(): Int = R.layout.fragment_card

    override fun getBindingVariable(): Int = BR.ViewModel

    override fun getLayoutBinding(binding: FragmentCardBinding) {
        this.binding = binding
    }

    override fun getViewModel(): CardViewModel = mYViewModel

    val mYViewModel: CardViewModel by viewModel()

    var voguePayInitializer: VoguePayInitializer? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        voguePayInitializer = (activity as VoguePayActivity).voguePayInitializer

        val filter = Linkify.TransformFilter { match, url -> "" }
        val pattern = Pattern.compile("()PCI-DSS COMPLIANT")
        Linkify.addLinks(binding.voguePcidssCompliantTv, pattern, "https://www.pcisecuritystandards.org/pci_security/", null, filter)



        binding.vogueCardExpiryTv.addTextChangedListener(ExpiryWatcher(binding.vogueCardExpiryTv))
        binding.voguePayButton.setOnClickListener(this)

        voguePayInitializer?.clientEmail?.let {
            if (
                Utils.isEmailValid(it)) {
                binding.vogueEmailTil.visibility = GONE
                binding.vogueEmailTv.setText(it)
            }

        }

        val amountToPay = voguePayInitializer?.total
        if (amountToPay != null) {
            if (amountToPay > 0) {
                binding.vogueAmountTil.visibility = GONE
                binding.vogueAmountTV.setText(amountToPay.toString())
            }
        }

        mYViewModel.uiData.observe(this, Observer<PaymentUIModel> {
            if (it != null) {
                Toast.makeText(context, "data not null", LENGTH_SHORT)
                    .show()
            }
        })

        mYViewModel.searchEvent.observe(this, Observer { searchEvent ->
        })

        mYViewModel.getResponse()
    }


    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.vogue_payButton -> {
                validateDetails()

            }
        }

    }

    fun validateDetails() {
        clearErrors()
        activity?.let { hide_keyboard(it) }

        var valid = true

        val amount = binding.vogueAmountTV.text.toString()
        val email = binding.vogueEmailTv.text.toString()
        val cvv = binding.vogueCvvTv.text.toString()
        val expiryDate = binding.vogueCardExpiryTv.text.toString()
        val cardNo = binding.vogueCardNoTv.text.toString()

        try {
            val amnt = java.lang.Double.parseDouble(amount)

            if (amnt <= 0) {
                valid = false
               binding.vogueAmountTV.error = "Enter a valid amount"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            valid = false
            binding.vogueAmountTV.error = "Enter a valid amount"
        }


        if (!Utils.isEmailValid(email)) {
            valid = false
            binding.vogueEmailTv.error = "Enter a valid email"
        }

        if (cvv.length < 3) {
            valid = false
            binding.vogueCvvTv.error = "Enter a valid cvv"
        }

        if (expiryDate.length != 5) {
            binding.vogueCardExpiryTv.error = "Enter a valid expiry date"
            valid = false
        }

        val cardNoStripped = cardNo.replace("\\s".toRegex(), "")

        if (cardNoStripped.length < 12) run {
            valid = false
            binding.vogueCardNoTv.error = "Enter a valid credit card number"
        }

        if (valid) {
            voguePayInitializer?.total = amount.toInt()
        }

        val command_api_token = "3b75Qd2Jjqm8gm9pGBDB3xYhMD3wc"
        val merchant_email_on_voguepay = "vicformidable@gmail.com"
        val ref =
            Calendar.getInstance().timeInMillis.toString() + Random().nextInt(9999999).toString()
//        hashStrings(command_api_token, voguePayInitializer?.task, merchant_email_on_voguepay )
        //make request
//        val payload = Payload(, Card(), )


    }

    private fun clearErrors() {
        binding.vogueAmountTV.error = null
        binding.vogueCvvTv.error = null
        binding.vogueCardExpiryTv.error = null
        binding.vogueCardNoTv.error = null

    }


    fun getLink() {
        mYViewModel.getPaymentLink(data = "")

    }

    fun testHisCode() {


    }

}