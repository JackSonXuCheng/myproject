package com.myproject.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by study on 6/26/2015 11:30.
 */
public final class HttpClientUtils {

    private HttpClientUtils() {
    }

    /**
     * post xml
     *
     * @param url
     * @param xml
     * @return
     */
    public static String postXml(String url, String xml) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        client = WebClientDevWrapper.wrapClient(client);
        HttpPost post = new HttpPost(url);
        String response = null;
        InputStream instream = null;
        try {
            StringEntity s = new StringEntity(xml, "UTF-8");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/xml");
            post.setEntity(s);
            HttpResponse res = null;
            if (client != null) {
                res = client.execute(post);
            }
            if (res != null && res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                SAXReader reader = new SAXReader();
                instream = entity.getContent();
                Document document = reader.read(new InputStreamReader(instream, "utf-8"));
                response = document.asXML();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (instream != null) {
                instream.close();
            }
        }
        return response;
    }

    /**
     * post json
     *
     * @param url
     * @param json
     * @return
     */
    public static JSONObject postJson(String url, String json) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        client = WebClientDevWrapper.wrapClient(client);
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        InputStream instream = null;
        try {
            StringEntity s = new StringEntity(json, "UTF-8");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = null;
            if (client != null) {
                res = client.execute(post);
            }
            if (res != null && res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String charset = "utf-8";
                instream = entity.getContent();
                response = new JSONObject(new JSONTokener(new InputStreamReader(instream, charset)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (instream != null) {
                instream.close();
            }
        }
        return response;
    }


    /**
     * post 键值对
     *
     * @param url
     * @param data 键值对内容
     * @return
     */
    public static String postKeyValue(String url, List<BasicNameValuePair> data) {


        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost(url);


            //url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(data, "UTF-8");
            post.setEntity(uefEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            try {
                if (null != entity) {
                    String result = EntityUtils.toString(entity);
                    return result;
                }
            } finally {
                httpResponse.close();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeHttpClient(httpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * get json
     *
     * @param url
     * @return
     */
    public static JSONObject get(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        client = WebClientDevWrapper.wrapClient(client);
        HttpGet get = new HttpGet(url);
        JSONObject response = null;
        InputStream instream = null;
        try {
            HttpResponse res = null;
            if (client != null) {
                res = client.execute(get);
            }
            if (res != null && res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                instream = entity.getContent();
                response = new JSONObject(new JSONTokener(new InputStreamReader(instream, "utf-8")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (instream != null) {
                instream.close();
            }
        }
        return response;
    }

    private static class WebClientDevWrapper {
        public static HttpClient wrapClient(HttpClient base) {
            try {
                RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
                ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
                registryBuilder.register("http", plainSF);
                //指定信任密钥存储对象和连接套接字工厂
                try {
                    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, new AnyTrustStrategy()).build();
                    LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                    registryBuilder.register("https", sslSF);
                } catch (KeyStoreException e) {
                    throw new RuntimeException(e);
                } catch (KeyManagementException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                Registry<ConnectionSocketFactory> registry = registryBuilder.build();
                //设置连接管理器
                PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
                connManager.setDefaultConnectionConfig(ConnectionConfig.custom().setCharset(Charset.forName("UTF-8")).build());
                connManager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(100000).build());
                //构建客户端
                return HttpClientBuilder.create().setConnectionManager(connManager).build();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private static class AnyTrustStrategy implements TrustStrategy {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }


    }


    private static CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private static void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }

}
