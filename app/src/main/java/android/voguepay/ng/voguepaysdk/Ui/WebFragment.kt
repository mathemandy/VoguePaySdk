package android.voguepay.ng.voguepaysdk.Ui

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.voguepay.ng.voguepaysdk.R
import android.voguepay.ng.voguepaysdk.Ui.payment.Constants.AUTHURL
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import org.apache.http.util.EncodingUtils

class WebFragment : Fragment() {


    private lateinit var mWebView: WebView
    private lateinit var postData: String
    internal var progessDialog: ProgressDialog? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_web, container, false)
        mWebView = v.findViewById(R.id.rave_webview)
        val bundle = arguments

        var  url : String? = null
        if (bundle != null){
            url = bundle.getString(AUTHURL)
            Log.e("", url)

        }
        onDisplayInternetBankingPage(url)
        return v

    }


    fun onDisplayInternetBankingPage(url : String?) {

        mWebView.settings.loadsImagesAutomatically = true
        mWebView.settings.javaScriptEnabled = true
        mWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        // Configure the client to use when opening URLs
        mWebView.webViewClient = MyBrowser()
        // Load the initial URL
        mWebView.loadUrl(url)

    }

    // Manages the behavior when URLs are loaded
    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.d("started URLS", url)
            showProgressIndicator(true)
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)

            //            Log.d("URLS", url);
            showProgressIndicator(false)

            Log.d("finished URLS", url)
            if (url.contains("/complete") || url.contains("submitting_mock_form")) {
                goBack()
            }
        }
    }


    fun goBack() {
        val intent = Intent()
        if (activity != null) {
            activity!!.setResult(1111, intent)
            activity!!.finish()
        }
    }


    fun showProgressIndicator(active: Boolean) {

        try {
            if (activity!!.isFinishing) {
                return
            }

            if (progessDialog == null) {
                progessDialog = ProgressDialog(activity)
                progessDialog!!.setCanceledOnTouchOutside(false)
                progessDialog!!.setMessage("Please wait...")
            }

            if (active && !progessDialog!!.isShowing) {
                progessDialog!!.show()
            } else {
                progessDialog!!.dismiss()
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }
}//Required empty public constructor
