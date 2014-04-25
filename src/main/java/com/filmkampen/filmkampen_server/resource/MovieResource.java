package com.filmkampen.filmkampen_server.resource;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

@Path("/movie")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    public void getMessage() {
        try {
            Client client = Client.create();
            String baseUrl = "http://api-eu.pandastream.com/v2/";
            String accessKey = "AKIAIWSOIXUUXTQRHHIA";
            String cloudId = "c6b2f9896d37fcafe2de9dc0ad53e028";
            String parameters = "access_key=" + accessKey + "&cloud_id=" + cloudId + "&timestamp=2014-04-21T15%3A39%3A10.260762Z";
            String signatureBeforeEncoding = "GET\napi.pandastream.com\n/videos.json\n" + parameters;
            String signature = encode("if5AGJE/JtqbAg7U8i13YaAiXrIWVFu9Onq/obp5", signatureBeforeEncoding);

            String url = baseUrl + "videos.json?" + parameters + "&signature=" + signature;
            System.out.println("URL:" + url);
            WebResource webResource = client.resource(url);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("\n============getCResponse============");
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String get() {
        Map<String, String> sParams = signedParams();
        String flattenParams = canonicalQueryString(sParams);
        System.out.println("request:" + flattenParams);
        String requestUrl = "http://" + "api-eu.pandastream.com" + "/v2/" + "videos.json" + "?" + flattenParams;
        System.out.println(requestUrl);
        HttpGet httpGet = new HttpGet(requestUrl);
        DefaultHttpClient httpclient = new DefaultHttpClient();

        String stringResponse = null;

        try {
            HttpResponse response = httpclient.execute(httpGet);
            stringResponse = EntityUtils.toString(response.getEntity());
            System.out.println(stringResponse);
        } catch(IOException e){
            e.printStackTrace();
        }

        return stringResponse;
    }

    private String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        char[] hex = Hex.encode(sha256_HMAC.doFinal(data.getBytes()));
        return new String(Base64.encode(new String(hex)));
    }
    
    private Map<String, String> signedParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cloud_id", "c6b2f9896d37fcafe2de9dc0ad53e028");
        params.put("access_key", "AKIAIWSOIXUUXTQRHHIA");
        
        params.put("timestamp", new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" ).format(new Date()));
        params.put("signature", generateSignature("GET", "/videos.json", "api-eu.pandastream.com", "if5AGJE/JtqbAg7U8i13YaAiXrIWVFu9Onq/obp5", params));
        return params;
    }

    private static String generateSignature(String method, String url, String host, String secretKey, Map<String,String> params) {
        String queryString = canonicalQueryString(params);
        String stringToSign = method.toUpperCase() + "\n" + host + "\n" + url + "\n" + queryString;
        
        System.out.println("sign:" + stringToSign);

        String signature = null;

        try {

            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(stringToSign.getBytes());

            signature = new String(Base64.encode(rawHmac));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return signature;
    }

    private static String canonicalQueryString(Map<String,String> map) {
        List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();

        for(Map.Entry<String, String> entry : map.entrySet()) {
            qparams.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));    
        }

        Comparator<BasicNameValuePair> comparator = new Comparator<BasicNameValuePair>() {
            public int compare(BasicNameValuePair o1, BasicNameValuePair o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(qparams, comparator);

      return URLEncodedUtils.format(qparams, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E","~");
    }
    
    public static void main(String[] args) {
        MovieResource resource = new MovieResource();
        //resource.getMessage();
        resource.get();
    }
}

    
   