package android.voguepay.ng.voguepaysdk2

import com.google.gson.annotations.SerializedName
import java.net.URLEncoder


import java.util.ArrayList



class Payload {

    var task: String? = "card"
    @SerializedName("merchant")
    var merchant_id: String? = "8302-0053889"
    var ref: String? = ""
    var hash: String = ""
    //compulsory fields
    var total: Int = 10
    @SerializedName("email")
    var buyer_email: String = "andyeshiet@gmail.com"// Email Address of Card Owner
    var merchant_ref: String = "ref123" // This will be returned to you on transaction requery
    var currency: String = "NGN"  // Supported Currencies in Nigeria
    var memo: String = "For Shoe Payment" // description of transaction (Must be encoded)
    var referral_url: String = "" // Shpuld be the Url the user was before he arrived here
    var response_url: String = URLEncoder.encode("http://f3c45a65.ngrok.io/customers", "UTF-8")  // Transaction ID is posted to this url (Must be encoded)
    var card : Card? = null
    //optional Fields
    var demo: Boolean = false
    var phone : String = "08120088124"//Phone number of card owner
    //Descriptor Information (Optional)
    var company : String = "test"
    var  city : String = "ikeja"
    var street : String = "56 thgb"
    var country : String = "NGA"

    constructor(
        task: String?,
        merchant_id: String?,
        ref: String?,
        hash: String,
        total: Int,
        buyer_email: String,
        merchant_ref: String,
        currency: String,
        memo: String,
        referral_url: String,
        response_url: String,
        card: Card?,
        demo: Boolean,
        phone: String,
        company: String,
        city: String,
        street: String,
        country: String
    ) {
        this.task = task
        this.merchant_id = merchant_id
        this.ref = ref
        this.hash = hash
        this.total = total
        this.buyer_email = buyer_email
        this.merchant_ref = merchant_ref
        this.currency = currency
        this.memo = memo
        this.referral_url = referral_url
        this.response_url = response_url
        this.card = card
        this.demo = demo
        this.phone = phone
        this.company = company
        this.city = city
        this.street = street
        this.country = country
    }

    constructor(ref: String?, hash: String, card: Card?) {
        this.ref = ref
        this.hash = hash
        this.card = card
    }


}

