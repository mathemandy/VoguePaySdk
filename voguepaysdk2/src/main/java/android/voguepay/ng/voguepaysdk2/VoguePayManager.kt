package android.voguepay.ng.voguepaysdk2

import android.app.Activity
import android.content.Intent
import android.voguepay.ng.voguepaysdk2.screens.VoguePayActivity
import java.net.URLEncoder
import kotlin.math.absoluteValue

private var TAG = LogHelper.makeLogTag(VoguePayManager::class.java)

class VoguePayManager(

    val clientEmail: String?,
    val merchantEmail: String?,
    var transactionAmount: Int?,
    val txRef: String?,
    val narration: String?,
    val currencyCode: String?,
    val countryCode: String?,
    val firstName: String?,
    val lastName: String?,
    var activity: Activity?,
    val withCard: Boolean?,
    val withAccount: Boolean?,
    val staging: Boolean?,
    val allowSaveCard: Boolean?,
    val merchant_id: String?,
    val ref: String?,
    val hash: String?,
    val merchant_ref: String?,
    val memo: String?,
    var referral_url: String?,
    val theme: Int?,
    val response_url: String?,
    val card: Card?,
    //optional Fields
    val demo: Boolean?,
    val phone: String?,
    //Descriptor Information (Optional)
    val company: String?,
    val city: String?,
    val street: String?,
    val task: String?,
    val total: Int?
) {

    data class Builder(

        private var clientEmail: String? = null,
        private var merchantEmail: String? = null,
        private var transactionAmount: Int? = null,
        private var txRef: String? = null,
        private var narration: String? = null,
        private var currencyCode: String? = null,
        private var countryCode: String? = null,
        private var firstName: String? = null,
        private var lastName: String? = null,
        private var activity: Activity? = null,
        private var withCard: Boolean? = null,
        private var withAccount: Boolean? = null,
        private var staging: Boolean? = null,
        private var allowSaveCard: Boolean? = null,
        private var merchant_id: String? = null,
        private var ref: String? = null,
        private var hash: String? = null,
        private var merchant_ref: String? = null,
        private var memo: String? = null,
        private var referral_url: String? = null,
        private var theme: Int? = null,
        private var response_url: String? = null,
        private var card: Card? = null,
        //optional Fields
        private var demo: Boolean? = null,
        private var phone: String? = null,
        //Descriptor Information (Optional)
        private var company: String? = null,
        private var city: String? = null,
        private var street: String? = null,
        private var task: String? = null,
        private var total: Int? = null


    ) {
        fun responseUrl(responseUrl: String) = apply {
            URLEncoder.encode(
                if (responseUrl.isNotBlank()) responseUrl else "http://f3c45a65.ngrok.io/customers",
                "UTF-8"
            )
        }

        fun setActivity(activity: Activity?) = apply { this.activity = activity }

        fun allowSaveCardFeature(allowSaveCard: Boolean) =
            apply { this.allowSaveCard = allowSaveCard }

        fun withAcccount(withAccount: Boolean) = apply { this.withAccount = withAccount }
        fun withCard(withCard: Boolean) = apply { this.withCard = withCard }
        fun isDemoMode(demo: Boolean) = apply { this.demo = demo }
        fun setMemo(memo: String?) = apply { this.memo = memo }
        fun setMerchantId(merchant_id: String) = apply { this.merchant_id = merchant_id }
        fun setMerchantEmail(merchant_Email: String) = apply { this.merchantEmail = merchant_Email }
        fun setTheme(theme: Int) =
            apply { this.theme = if (theme.isEmpty()) R.style.DefaultTheme else theme }

        fun setTransactionurl(url: String) =
            apply { this.response_url = URLEncoder.encode(url, "UTF-8") }

        fun setPhoneNumber(number: String) = apply { this.phone = number.trim() }
        fun setCity(city: String) = apply { this.city = city }
        fun setMerchantRef(merchant_ref: String) = apply { this.merchant_ref = merchant_ref }

        fun setStreet(street: String) = apply { this.street = street }

        fun setCurrency(currency: String) = apply { this.currencyCode = currency }

        fun setTransactionAmount(amount: Int?) = apply { this.transactionAmount = amount }

        fun setCountryCode(country: String) = apply { this.countryCode = country }

        fun setFirstName(fName: String) = apply { this.firstName = fName }

        fun onStagingEnv(staging: Boolean) = apply { this.staging = staging }

        fun setlastName(lName: String) = apply { this.lastName = lName }

        fun setBuyerEmail(email: String) = apply { this.clientEmail = email }

        fun setCommandTokenApi(token: String) = apply { this.txRef = token }

        fun setHash(hash: String) = apply { this.hash = hash }

        fun build() = VoguePayManager(
            clientEmail = clientEmail,
            allowSaveCard = allowSaveCard,
            activity = activity,
            card = card,
            city = city,
            company = company,
            countryCode = countryCode,
            currencyCode = currencyCode,
            demo = demo,
            firstName = firstName,
            hash = hash,
            lastName = lastName,
            memo = memo,
            merchant_id = merchant_id,
            merchant_ref = merchant_ref,
            merchantEmail = merchantEmail,
            narration = narration,
            phone = phone,
            ref = merchant_ref,
            referral_url = referral_url,
            response_url = response_url,
            staging = staging,
            street = street,
            task = task,
            theme = theme,
            total = total,
            transactionAmount = transactionAmount,
            txRef = txRef,
            withAccount = withAccount,
            withCard = withCard
        )

        private fun createVoguePayInitializer(): VoguePayInitializer {
            return VoguePayInitializer(
                clientEmail = clientEmail,
                allowSaveCard = allowSaveCard,
                card = card,
                city = city,
                company = company,
                countryCode = countryCode,
                currencyCode = currencyCode,
                demo = demo,
                firstName = firstName,
                hash = hash,
                lastName = lastName,
                memo = memo,
                merchant_id = merchant_id,
                merchant_ref = merchant_ref,
                merchantEmail = merchantEmail,
                narration = narration,
                phone = phone,
                ref = merchant_ref,
                referral_url = referral_url,
                response_url = response_url,
                staging = staging,
                street = street,
                task = task,
                theme = theme,
                total = total,
                transactionAmount = transactionAmount,
                txRef = txRef,
                withAccount = withAccount ?: error("With Account Value muse be set"),
                withCard =  withCard ?: error("WithCard Value mus be set ")
            )
        }

        fun initialize() {
            if (activity != null) {
                var intent = Intent(activity, VoguePayActivity::class.java)
                intent.putExtra(VoguePayConstants.VOGUEPAY_PARAMS, createVoguePayInitializer())
                activity!!.startActivityForResult(
                    intent,
                    VoguePayConstants.ROGUEPAY_REQUEST_CODE
                )
            } else {
                LogHelper.e(TAG, "Context is Required")

            }
        }


    }
}


fun Int.isEmpty(): Boolean {
    return this.absoluteValue <= 0
}



