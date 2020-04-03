package download.image.thread;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImageThread implements Runnable{
    private Activity activity;
    private String image_url;
    private Context context;
    private LinearLayout linearLayout;
    public DownloadImageThread(Activity activity, Context context, String image_url, LinearLayout linearLayout){
        this.activity=activity;
        this.image_url=image_url;
        this.context=context;
        this.linearLayout=linearLayout;
    }
    @Override
    public void run(){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        URL url=null;
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        File file=null;
        FileOutputStream fileOutputStream=null;
        String image_name=null;
        byte[]  image_stream=null;
        try {
            url=new URL(image_url);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            inputStream=httpURLConnection.getInputStream();
            image_stream=new byte[1024];
            image_name= Uri.parse(image_url).getLastPathSegment();
            file=new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/"+image_name);
            fileOutputStream=new FileOutputStream(file);
            int read=-1;
            while ((read = inputStream.read(image_stream)) != -1){
                    fileOutputStream.write(image_stream,0,read);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            activity.runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           linearLayout.setVisibility(View.GONE);
                                       }
        });
        //Toast.makeText(context,"Image Download Successfully",Toast.LENGTH_LONG).show();
    }
}
}
