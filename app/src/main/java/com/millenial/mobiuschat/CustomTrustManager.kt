package com.millenial.mobiuschat

import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

object CustomTrustManager {
    private var trustManager: X509TrustManager? = null

    fun initialize(): X509TrustManager {

        trustManager = object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        }

        return trustManager ?: throw IllegalStateException("TrustManager not initialized")
    }

//    fun getTrustManager(): X509TrustManager {
//        return  trustManager ?: throw IllegalStateException("TrustManager not initialized")
//    }

}

