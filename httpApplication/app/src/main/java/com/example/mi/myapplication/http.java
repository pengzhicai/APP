package com.example.mi.myapplication;

import android.content.Context;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by mi on 18-10-11.
 */
public class http {

    //方法：发送网络请求，获取百度首页的数据。在里面开启线程
    public static void sendRequestWithHttpClient(final Context context) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpCient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpPost httpPost = new HttpPost("http://132.232.142.84:8080/SpringMVC_SSM/test/createJira.do");

                try {
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpCient.execute(httpPost);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");//将entity当中的数据转换为字符串
                        // Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        Log.d("PZC", response);
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d("PZC", String.valueOf(e));
                    e.printStackTrace();
                }

            }
        }).start();
    }
}