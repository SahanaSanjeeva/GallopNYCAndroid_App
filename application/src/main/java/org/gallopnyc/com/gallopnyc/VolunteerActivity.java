package org.gallopnyc.com.gallopnyc;

/**
 * Created by SahanaSanjeeva on 11/30/17.
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class VolunteerActivity extends Fragment implements View.OnClickListener {

    LayoutInflater layoutInflater;
    View rootView;
    Button signUpBtn, searchBtn;
    ViewGroup containerGroup;
    String signUpStr = "https://volunteer.gallopnyc.org/HOC__Volunteer_Registration_Page";
    String searchStr = "https://volunteer.gallopnyc.org/HOC__Volunteer_Opportunity_Search_Page";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater;
        containerGroup = container;
        rootView = inflater.inflate(R.layout.tab2volunteer, container, false);

        signUpBtn = (Button) rootView.findViewById(R.id.signUpBtn);
        searchBtn = (Button) rootView.findViewById(R.id.searchBtn);

        signUpBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        return rootView;

            /*public void onClick(View v) {
                //loadWebUrl(signUpStr);
                Log.v("volunteer", "------signup");
                pageView = layoutInflater.inflate(R.layout.weblinkdisplay, containerGroup, false);
                //loadWebUrl(signUpStr, rootView);
                Log.v("volunteer", "------signup  3");
                WebView webView;
                webView = (WebView) pageView.findViewById(R.id.weblinkview);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setDomStorageEnabled(true);
                webView.setWebViewClient(new WebViewClientCallback());
                webView.loadUrl("https://volunteer.gallopnyc.org/HOC__Volunteer_Registration_Page");

                //containerGroup.removeView(rootView);
                //containerGroup.addView(pageView);
                //containerGroup.bringChildToFront(pageView);

                //rootView.invalidate();

                getActivity().setContentView(pageView);
            }
        });*/



        /*    public void onClick(View v) {
                rootView = layoutInflater.inflate(R.layout.weblinkdisplay, null);
                loadWebUrl(searchStr, rootView);
            }
        });*/
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
        case R.id.signUpBtn:
            loadWebUrl(signUpStr, rootView);
            break;

            case R.id.searchBtn:
            loadWebUrl(searchStr, rootView);
            break;
        }
    }

    private void loadWebUrl(String webUrlStr, View mainRootView) {
        /*pageView = layoutInflater.inflate(R.layout.weblinkdisplay, containerGroup, false);
        WebView webView = (WebView) pageView.findViewById(R.id.weblinkview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClientCallback());
        webView.loadUrl(webUrlStr);

        getActivity().setContentView(pageView);*/

        Bundle args = new Bundle();
        args.putString("urlString", webUrlStr);
        ClickWebView fragment = new ClickWebView();
        fragment.setArguments(args);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.volunteerLayout, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(this.getTag());
        transaction.commit();
        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        if (signUpBtn.getVisibility() == View.VISIBLE) {
                            signUpBtn.setVisibility(View.GONE);
                            searchBtn.setVisibility(View.GONE);
                        } else {
                            signUpBtn.setVisibility(View.VISIBLE);
                            searchBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });

        //if(signUpBtn.isShown()) {

        //} else {
        //    signUpBtn.setVisibility(View.VISIBLE);
        //}
        //transaction.commit();
        //fragment.getView().setFocusableInTouchMode(true);
        //fragment.getView().requestFocus();

        /*fragment.getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK ) {
                   // signUpBtn.setVisibility(View.VISIBLE);
                    return true;
                } else {
                   // signUpBtn.setVisibility(View.GONE);
                }
                return false;
            }
        } );*/
        //signUpBtn.setVisibility(View.GONE);

        //Intent intent = new Intent(getActivity(), ClickWebView.class);
        //intent.putExtra("urlString", webUrlStr);

        //startActivity(intent);
        //Log.v("volunteer", "------signup  2");
        //ClickWebView clickClass = new ClickWebView(contextActive);

    }

    private class WebViewClientCallback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }
}
