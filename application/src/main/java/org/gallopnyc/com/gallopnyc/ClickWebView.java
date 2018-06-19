package org.gallopnyc.com.gallopnyc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sahanasanjeeva on 3/8/18.
 */

public class ClickWebView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
        Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weblinkdisplay, container, false);
        Bundle arguments = getArguments();
        String webUrlString = arguments.getString("urlString");
        loadPage(rootView, webUrlString);

        return rootView;
    }

    public void loadPage(View root, String urlText) {
        WebView webView = (WebView) root.findViewById(R.id.weblinkview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClientCallback());
        webView.loadUrl(urlText);
    }

    private class WebViewClientCallback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }
}
