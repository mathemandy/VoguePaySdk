package android.voguepay.ng.voguepaysdk.Ui

import android.content.Intent
import android.os.Bundle
import android.voguepay.ng.voguepaysdk.R
import android.voguepay.ng.voguepaysdk.Ui.payment.Constants.AUTHURL
import android.voguepay.ng.voguepaysdk2.LogHelper
import android.voguepay.ng.voguepaysdk2.Utils
import android.voguepay.ng.voguepaysdk2.VoguePayConstants
import android.voguepay.ng.voguepaysdk2.VoguePayManager
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : DaggerAppCompatActivity(), MainFragment.fragmentTransactionListener {



    private var fragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPaymentBtn.setOnClickListener{
            validateEntries()
        }
//        loadMainFragment()
    }
    private fun loadMainFragment() {
        fragment = MainFragment()

        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.parentContainer, fragment as MainFragment, fragment!!.javaClass.simpleName)
                .addToBackStack(null).commit()
        }

    }
    override fun PaymentButtonClicked(responseUrl: String) {
        fragment = WebFragment()
        val bundle = Bundle()
        bundle.putString(AUTHURL, responseUrl)
        if (fragment != null) {
            fragment?.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.parentContainer, fragment as WebFragment, fragment!!.javaClass.simpleName)
                .addToBackStack(null).commit()
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 2) {
            supportFragmentManager.popBackStack()
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }
        super.onBackPressed()
    }

    private fun validateEntries(){
        clearErrors()
        var mEmail =   merchantEmail.text.toString()
        var bEmail = buyerEmail.text.toString()
        var amoutToCharge = amountTC.text.toString()
        var commandApiToken = comApiToken.text.toString()
        var currency = currencyTC.text.toString()
        var fname = fNameTC.text.toString()
        var lName = lnameTC.text.toString()
        var country = countryTC.text.toString()
        var city = cityYC.text.toString()
        var street = streetTC.text.toString()
        var company = companyTC.text.toString()
        var phone = phoneTC.text.toString()
        var responseUrl  = responseTC.text.toString()
        var memo = memoTC.text.toString()
        var merchanRef = merchantRef.text.toString()
        var merchantId = merchant_id.text.toString()

        var valid = true
        if (amoutToCharge.isEmpty()){
            amoutToCharge = "0"
        }
        //Check for compulsory Fields
        if (merchantId.isEmpty()){
            valid = false
            merchant_id.error = "A merchant id"
        }
        if (amoutToCharge.isEmpty()){
            valid = false
            amountTC.error = "Enter Amount to Charge"
        }
        if (memo.isEmpty()){
            valid = false
            memoTC.error = "Discription of what you are paying for is required"
        }
        if(responseUrl.isEmpty()){
            valid= false
        responseTC.error ="A response Url is Required"
        }
        if (!Utils.isEmailValid(mEmail)){
            valid = false
            merchantEmail.error = "A valid email is required"
        }
        if (!Utils.isEmailValid(bEmail)){
            valid = false
            buyerEmail.error = "A valid email is required"
        }
        if (commandApiToken.isEmpty()){
            valid = false
            comApiToken.error = "A  Command API key is required"
        }
        if (currency.isEmpty()){
            valid = false
            currencyTC.error = "A valid currency code is Required"
        }
        if (country.isEmpty()) {
            valid = false
            countryTC.error = "A valid country code is required"
        }
        if (valid){
            VoguePayManager(this)
                .setAmount(amoutToCharge.toInt())
                .setCountry(country)
                .setCity(city)
                .setCommandTokenApi(commandApiToken)
                .setCurrency(currency)
                .setfName(fname)
                .setMerchantRef(merchanRef)
                .setMerchantEmail(mEmail)
                .setStreet(street)
                .setPhoneNumber(phone)
                .setlName(lName)
                .setMemo(memo)
                .setHash(Utils.hashStrings(commandApiToken,
                    if (cardPaymentSwitch.isChecked ) "card" else "pay",  mEmail,
                    Calendar.getInstance().timeInMillis.toString() + Random().nextInt(9999999).toString() ))
                .setMerchantId(merchantId)
                .setTransactionurl(responseUrl)
                .setBuyerEmail(bEmail)
                .allowSaveCardFeature(cardPaymentSwitch.isChecked)
                .isDemoMode(isDemoSwitch.isChecked)
                .initialize()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == VoguePayConstants.ROGUEPAY_REQUEST_CODE && data != null) {
            val message = data.getStringExtra("response")

            if (message != null) {
                LogHelper.e("rave response", message)
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
    private fun clearErrors(){
        merchantEmail.error = null
        buyerEmail.error = null
        amountTC.error = null
        comApiToken.error = null
        currencyTC.error = null
        fNameTC.error = null
        lnameTC.error = null
        countryTC.error = null
        cityYC.error = null
        streetTC.error = null
        companyTC.error = null
        phoneTC.error = null
        responseTC.error = null
        memoTC.error = null
        merchantRef.error = null
        merchant_id.error = null
    }


}

