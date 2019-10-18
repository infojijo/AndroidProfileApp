@file:Suppress("DEPRECATION")

package network

import android.annotation.SuppressLint
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiConnectionModule {

    private var iApiEndPoints: IApiEndPoints? = null

    /**
     * @return -IApiEndPoints with the retrofit instance.
     */
    val instance: IApiEndPoints
        get() {
            if (iApiEndPoints == null) {
                val builder = Retrofit.Builder().baseUrl("https://cjnet.in/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                var httpClient = OkHttpClient.Builder()
                if ("https://cjnet.in/".indexOf("http") >= 0) {
                    httpClient = unsafeOkHttpClient.newBuilder()
                }
                iApiEndPoints =
                    builder.client(httpClient.build()).build().create(IApiEndPoints::class.java)
            }
            return iApiEndPoints!!
        }

    /**
     * @return OkHttpClient -supports unsafe http connections to establish contact with the app.
     */
    val unsafeOkHttpClient: OkHttpClient
        get() {
            try {
                val trustManagers = arrayOf<TrustManager>(object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        x509Certificates: Array<java.security.cert.X509Certificate>,
                        s: String
                    ) {
                        //do nothing
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        x509Certificates: Array<java.security.cert.X509Certificate>,
                        s: String
                    ) {
                        //do nothing
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })

                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                client.interceptors().add(httpLoggingInterceptor)
                client.readTimeout(30, TimeUnit.SECONDS)
                client.connectTimeout(30, TimeUnit.SECONDS)
                val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(null, null)
                val sslContext = SSLContext.getInstance("TLS")
                val trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                sslContext.init(null, trustManagers, SecureRandom())
                client.sslSocketFactory(sslContext.socketFactory)
                    .hostnameVerifier { _, _ -> true }
                return client.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }

}
