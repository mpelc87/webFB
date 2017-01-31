package pelc.marek.webfb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webview);
        WebViewClient webViewClient = new CustomWebViewClient(this);
        CustomWebChromeClient webChromeClient = new CustomWebChromeClient();
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.97 Safari/537.36");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://m.facebook.com");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
    }
}

class CustomWebViewClient  extends WebViewClient {
    Context context;
    public CustomWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.startsWith("https://m.facebook.com")) {
            view.loadUrl(url);
            return true;
        }
        if (url.startsWith("https://") || url.startsWith("http://")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
            return true;
        }
        return false;
    }
}

class CustomWebChromeClient extends WebChromeClient {

}