package android.voguepay.ng.voguepaysdk2

import android.app.Activity
import android.content.Intent
import java.net.URLEncoder

class VoguePayManager(activity: Activity) {
    private var TAG = LogHelper.makeLogTag(VoguePayManager::class.java)
    private var email: String? = null
    private var amount: Int? = null
    private var txRef: String? = null
    private var narration = ""
    private var currency = "NGN"
    private var country = "NG"
    private var fName = ""
    private var lName = ""
    private var activity: Activity? = null
    private var withCard = true
    private var withAccount = true
    private var staging = true
    private var allowSaveCard = true
    private var merchant_id: String? = ""
    private var ref: String? = ""
    private var hash: String? = ""
    private var buyer_email: String? = ""
    private var merchant_ref: String? = ""
    private var memo: String? = ""
    private var referral_url: String = ""
    private var response_url: String = URLEncoder.encode(
        "http://f3c45a65.ngrok.io/customers",
        "UTF-8"
    ) // Transaction ID is posted to this url (Must be encoded)
    private var card: Card? = null
    //optional Fields
    var demo: Boolean = false
    var phone: String? = null
    //Descriptor Information (Optional)
    var company: String = "test"
    var city: String = "ikeja"
    var street: String = "56 thgb"
    var task: String = "card"
    var total: Int? = 0
    private var merchant_Email: String = ""


    init {
        this.activity = activity
    }

    fun allowSaveCardFeature(allowSaveCard: Boolean): VoguePayManager {
        this.allowSaveCard = allowSaveCard
        return this
    }

    fun withAcccount(withAccount : Boolean) : VoguePayManager {
        this.withAccount = withAccount
        return  this

    }

    fun withCard(withCard : Boolean) : VoguePayManager {
        this.withCard = withCard
        return  this
    }


    fun isDemoMode(demo: Boolean): VoguePayManager {
        this.demo = demo
        return this
    }

    fun setMemo(memo: String?): VoguePayManager {
        this.memo = memo
        return this
    }

    fun setMerchantId(merchant_id: String): VoguePayManager {
        this.merchant_id = merchant_id
        return this
    }

    fun setMerchantEmail(merchant_Email: String): VoguePayManager {
        this.merchant_Email = merchant_Email
        return this
    }

    fun setTransactionurl(url: String): VoguePayManager {
        this.response_url = URLEncoder.encode(url, "UTF-8")
        return this
    }

    fun setPhoneNumber(number: String): VoguePayManager {
        this.phone = number
        return this
    }

    fun setCity(city: String): VoguePayManager {
        this.city = city
        return this
    }

    fun setMerchantRef(merchannt_ref: String): VoguePayManager {
        this.merchant_ref = merchannt_ref
        return this
    }

    fun setStreet(street: String): VoguePayManager {
        this.street = street
        return this
    }


    fun setCurrency(currency: String): VoguePayManager {
        this.currency = currency
        return this
    }

    fun setAmount(amount: Int?): VoguePayManager {
        this.amount = amount
        return this
    }

    fun setCountry(country: String): VoguePayManager {
        this.country = country
        return this
    }


    fun setfName(fName: String): VoguePayManager {
        this.fName = fName
        return this
    }

    fun onStagingEnv(staging: Boolean): VoguePayManager {
        this.staging = staging
        return this
    }

    fun setlName(lName: String): VoguePayManager {
        this.lName = lName
        return this
    }

    fun setBuyerEmail(email: String): VoguePayManager {
        this.buyer_email = buyer_email
        return this
    }

    fun setCommandTokenApi(token: String): VoguePayManager {
        this.txRef = token
        return this
    }

    fun setHash(hash: String): VoguePayManager {
        this.hash = hash
        return this
    }

    fun initialize() {
        if (activity != null) {
            var intent = Intent(activity, VoguePayActivity::class.java)
            intent.putExtra(VoguePayConstants.VOGUEPAY_PARAMS, createVoguePayInitializer())
            activity!!.startActivityForResult(intent, VoguePayConstants.ROGUEPAY_REQUEST_CODE)
        } else {
            LogHelper.e(TAG, "Context is Required")

        }
    }

    fun createVoguePayInitializer(): VoguePayInitializer {
        return VoguePayInitializer(
            task,
            merchant_id,
            ref,
            hash,
            total,
            buyer_email,
            merchant_ref,
            currency,
            memo,
            referral_url,
            response_url,
            null,
            demo,
            phone, company, city, street, country,
            withCard,
            withAccount
        )
    }


}