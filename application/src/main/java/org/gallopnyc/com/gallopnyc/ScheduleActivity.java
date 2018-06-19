package org.gallopnyc.com.gallopnyc;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Sahana Sanjeeva on 1/17/18.
 */

public class ScheduleActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5schedule, container, false);

        loadSchedulePage(rootView);

        return rootView;
    }

    public void loadSchedulePage(View root) {
        WebView webView;
        webView = (WebView) root.findViewById(R.id.schedulewebview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClientCallback());
        webView.loadUrl("https://calendar.google.com/calendar/embed?src=gallopnyc.org_c0d5i9ae8sguq3nl7nuu5pad9g%40group.calendar.google.com&ctz=America/New_York");
        //webView.loadUrl("http://google.com");
    }

    private class WebViewClientCallback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }
}
