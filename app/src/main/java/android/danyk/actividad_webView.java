package android.danyk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;

public class actividad_webView extends AppCompatActivity {
    private WebView miwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_web_view);

        miwebView=(WebView) findViewById(R.id.webView);
        miwebView.setWebViewClient(new WebViewClient());
        miwebView.loadUrl("https://www.youtube.com/watch?v=mCdA4bJAGGk");
        WebSettings webSettings=miwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if(miwebView.canGoBack()) {
            miwebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}