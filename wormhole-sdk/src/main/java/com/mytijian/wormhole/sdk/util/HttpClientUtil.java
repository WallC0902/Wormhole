package com.mytijian.wormhole.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.protocol.https.Handler;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 * Created by wangchangpeng on 2017/5/22.
 */
public class HttpClientUtil {

    static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    protected static final String CHARSET = "UTF-8";
    static HostnameVerifier sslHostnameVerifier = createHostnameVerifier();
    static SSLSocketFactory sslSocketFactory = createSSLSocketFactory();

    public static String post(String urlStr, String data) {
        HttpURLConnection con = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(null, urlStr, new Handler());
            con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("charset", CHARSET);
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setUseCaches(false);

            if ((con instanceof HttpsURLConnection)) {
                HttpsURLConnection httpsCon = (HttpsURLConnection)con;
                httpsCon.setHostnameVerifier(sslHostnameVerifier);
                httpsCon.setSSLSocketFactory(sslSocketFactory);
            }

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(data.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            int HttpResult = con.getResponseCode();
            if (HttpResult == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
            } else {
                LOGGER.info(con.getResponseMessage());
            }
        } catch (Exception e) {
            LOGGER.info("post", e);
        } finally {
            if (con != null)
                con.disconnect();
        }
        return sb.toString();
    }

    private static HostnameVerifier createHostnameVerifier()
    {
        return new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return (urlHostName != null) && (urlHostName.equals(session.getPeerHost()));
            }
        };
    }

    private static SSLSocketFactory createSSLSocketFactory()
    {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException
                {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException
                {
                }
            };
            context.init(null, new TrustManager[] { trustManager }, null);
            sslSocketFactory = context.getSocketFactory();
        }
        catch (Exception e) {
            LOGGER.error("createSSLSocketFactory", e);
        }
        return sslSocketFactory;
    }
}
