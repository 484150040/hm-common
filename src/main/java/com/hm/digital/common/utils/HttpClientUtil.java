package com.hm.digital.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author vconinfo
 * @createdOn 2019/8/19
 * @description: http工具类
 */
@Slf4j
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager pcm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";
    /**
     * 连接池最大连接数
     */
    private static int MAX_TOTAL = 300;
    /**
     * 每路由最大连接数，默认是2
     */
    private static int MAX_PER_ROUTE = 5;
    /**
     * 连接超时时间 单位毫秒
     */
    private static int CONN_TIME_OUT = 60000;
    /**
     * 从connect Manager获取connection超时时间 单位毫秒
     */
    private static int reqTimeout = 5000;

    private static void init() {
        if (pcm == null) {
            pcm = new PoolingHttpClientConnectionManager();
            // 整个连接池最大连接数
            pcm.setMaxTotal(MAX_TOTAL);
            // 每路由最大连接数，默认值是2
            pcm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        }
    }
    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(pcm).build();
    }

    /**
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url,String json) {
        HttpPost httpPost = new HttpPost(url);
        //解决中文乱码问题
        StringEntity entity = new StringEntity(json,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url,String json, Map<String, Object> headers) {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        //解决中文乱码问题
        StringEntity entity = new StringEntity(json,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * 处理Http请求
     */
    private static String getResult(HttpRequestBase request) {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity);
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (IOException e) {
            log.error("" + e);
        }
        return EMPTY_STR;
    }

  /**
   * 发送GET请求
   *
   * @param url        目的地址
   * @param parameters 请求参数，Map类型。
   * @return 远程响应结果
   */
  public static String sendGet(String url, Map<String, String> parameters) {
    String result = "";
    BufferedReader in = null;// 读取响应输入流
    StringBuffer sb = new StringBuffer();// 存储参数
    String params = "";// 编码之后的参数
    try {
      // 编码请求参数
      if (parameters.size() == 1) {
        for (String name : parameters.keySet()) {
          sb.append(name).append("=").append(
              java.net.URLEncoder.encode(parameters.get(name),
                  "UTF-8"));
        }
        params = sb.toString();
      } else {
        for (String name : parameters.keySet()) {
          sb.append(name).append("=").append(
              java.net.URLEncoder.encode(parameters.get(name),
                  "UTF-8")).append("&");
        }
        String temp_params = sb.toString();
        params = temp_params.substring(0, temp_params.length() - 1);
      }
      String full_url = url + "?" + params;
      System.out.println(full_url);
      // 创建URL对象
      java.net.URL connURL = new java.net.URL(full_url);
      // 打开URL连接
      java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
          .openConnection();
      // 设置通用属性
      httpConn.setRequestProperty("Accept", "*/*");
      httpConn.setRequestProperty("Connection", "Keep-Alive");
      httpConn.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
      // 建立实际的连接
      httpConn.connect();
      // 响应头部获取
      Map<String, List<String>> headers = httpConn.getHeaderFields();
      // 遍历所有的响应头字段
      for (String key : headers.keySet()) {
        System.out.println(key + "\t：\t" + headers.get(key));
      }
      // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
      in = new BufferedReader(new InputStreamReader(httpConn
          .getInputStream(), "UTF-8"));
      String line;
      // 读取返回的内容
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }

  /**
   * 发送POST请求
   *
   * @param url        目的地址
   * @param parameters 请求参数，Map类型。
   * @return 远程响应结果
   */
  public static String sendPost(String url, Map<String, String> parameters) {
    String result = "";
    BufferedReader in = null;// 读取响应输入流
    StringBuffer sb = new StringBuffer();// 存储参数
    String params = "";// 编码之后的参数
    PrintWriter out = null;
    try {
      // 编码请求参数
      if (parameters.size() == 1) {
        for (String name : parameters.keySet()) {
          sb.append(name).append("=").append(
              java.net.URLEncoder.encode(parameters.get(name),
                  "UTF-8"));
        }
        params = sb.toString();
      } else {
        for (String name : parameters.keySet()) {
          sb.append(name).append("=").append(
              java.net.URLEncoder.encode(parameters.get(name),
                  "UTF-8")).append("&");
        }
        String temp_params = sb.toString();
        params = temp_params.substring(0, temp_params.length() - 1);
      }
      String full_url = url + "?" + params;
      System.out.println(full_url);
      // 创建URL对象
      java.net.URL connURL = new java.net.URL(full_url);
      // 打开URL连接
      java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
          .openConnection();
      // 设置通用属性
      httpConn.setRequestProperty("Accept", "*/*");
      httpConn.setRequestProperty("Connection", "Keep-Alive");
      httpConn.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
      // 设置POST方式
      httpConn.setDoInput(true);
      httpConn.setDoOutput(true);
      // 获取HttpURLConnection对象对应的输出流
      out = new PrintWriter(httpConn.getOutputStream());
      // 发送请求参数
      out.write(params);
      // flush输出流的缓冲
      out.flush();
      // 定义BufferedReader输入流来读取URL的响应，设置编码方式
      in = new BufferedReader(new InputStreamReader(httpConn
          .getInputStream(), "UTF-8"));
      String line;
      // 读取返回的内容
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }
  public static String httpPost2Json(String url, Map<String, String> parameters) throws UnsupportedEncodingException {
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS).build();
    ObjectMapper objectMapper = new ObjectMapper();
    MediaType mediaType = MediaType.parse("application/json");
    String param;
    try {
      param = objectMapper.writeValueAsString(parameters);
    } catch (JsonProcessingException e1) {
      return "";
    }
    RequestBody body = RequestBody.create(mediaType, param);
    Request request =
        new Request.Builder().url(url).post(body).addHeader("Content-Type", "application/json")
            .addHeader("cache-control", "no-cache").build();
    Response response;
    try {
      response = client.newCall(request).execute();
      int code = response.code();
      String str = response.body().string();
      str = replaceBlank(str);
      return str;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
  public static String replaceBlank(String str) {
    String dest = "";
    if (str != null) {
      Pattern p = Pattern.compile("\\s*|\t|\r|\n");
      Matcher m = p.matcher(str);
      dest = m.replaceAll("");
    }
    return dest;
  }

  public static Map<String, Object> objectToMap(Object object){
    Map<String,Object> dataMap = new HashMap<>();
    Class<?> clazz = object.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      try {
        field.setAccessible(true);
        dataMap.put(field.getName(),field.get(object));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return dataMap;
  }
  public static Map<String, Object> json2map(String str_json) {
    Map<String, Object> res = null;
    try {
      Gson gson = new Gson();
      res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
      }.getType());
    } catch (JsonSyntaxException e) {
    }
    return res;
  }
}
