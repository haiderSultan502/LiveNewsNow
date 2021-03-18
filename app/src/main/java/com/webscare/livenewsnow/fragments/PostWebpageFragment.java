package com.webscare.livenewsnow.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.R;

import org.w3c.dom.Document;

import java.io.IOException;

public class PostWebpageFragment extends Fragment {

    View view;
    WebView webView;
    ImageView imageNewsHome,imvBookMark,imvShare;

    Bundle bundle;
    static String newsUrl,newsThumbnail;
    Document document = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_post_webpage, container, false);

        initializeView();

        return view;
    }

    private void initializeView() {

        webView=view.findViewById(R.id.web_view);
        imageNewsHome = view.findViewById(R.id.img_news_home);
        imvShare = view.findViewById(R.id.imgv_share);
        imvBookMark = view.findViewById(R.id.imgv_bookmark);

//        MainActivity.animationShow();

        //If the web page you plan to load in your WebView uses JavaScript, you must enable JavaScript for your WebView

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        getBundleValues();
    }

    private void getBundleValues() {

        bundle=getArguments();

        newsUrl=bundle.getString("newsUrl");
        newsThumbnail=bundle.getString("newsThumbnail");

        Picasso.with(getActivity()).load(newsThumbnail).placeholder(R.drawable.loading).error(R.drawable.loading).into(imageNewsHome);

        webView.loadUrl(newsUrl);

        webView.setWebViewClient(new MyWebViewClient());  //  this is neccessary otherwise web page open in browser instead of webview

//        MainActivity.animationHide();




    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if ("www.livenewsnow.com".equals(Uri.parse(url).getHost())) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }


}
