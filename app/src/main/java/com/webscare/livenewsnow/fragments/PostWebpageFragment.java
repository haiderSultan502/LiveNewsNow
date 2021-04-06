package com.webscare.livenewsnow.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PostWebpageFragment extends Fragment {

    View view;
    WebView webView;
    ImageView imageNewsHome,imvBookMark,imvShare;

    static Document document = null;

    Bundle bundle;
    String newsUrl,newsThumbnail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }  // add comment


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_post_webpage, container, false);

        initializeView();

        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Get the web view hit test result
        final WebView.HitTestResult result = webView.getHitTestResult();

        String imgUrl = result.getExtra();

        Toast.makeText(getActivity(), imgUrl, Toast.LENGTH_SHORT).show();
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

        MainActivity.animationShow();
//
        getBundleValues();
    }

    private void getBundleValues() {

        bundle=getArguments();

        newsUrl=bundle.getString("newsUrl");

        newsThumbnail=bundle.getString("newsThumbnail");

        Picasso.with(getActivity()).load(newsThumbnail).placeholder(R.drawable.loading).error(R.drawable.loading).into(imageNewsHome);

//        webView.loadUrl(newsUrl);

        new MyAsynTask().execute();

//        MainActivity.animationHide();




    }

    private class MyAsynTask extends AsyncTask<Void,Void, Document>
    {


        @Override
        protected Document doInBackground(Void... voids) {
            removeViews();
            return document;
        }

        private void removeViews() {

            try {

                document = Jsoup.connect(newsUrl).get();

                document.getElementsByClass("td-header-menu-wrap").remove();
                document.getElementsByClass("td-footer-container").remove();
                document.getElementsByClass("td-sub-footer-container").remove();
                document.getElementsByClass("td-modal-image").remove();

//                document.getElementsByClass("td-sub-footer-container td-container td-container-border").remove();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        @Override
        protected void onPostExecute(final Document document) {

            super.onPostExecute(document);

            webView.setWebViewClient(new MyWebViewClient());

            webView.loadDataWithBaseURL(newsUrl,document.toString(),"text/html","utf-8","");

            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

//            webView.setWebViewClient(new WebViewClient()
//            {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    return super.shouldOverrideUrlLoading(view, url);
//                }
//            });

            MainActivity.animationHide();

        }


//

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if ("www.livenewsnow.com".equals(Uri.parse(url).getHost())) {
//                 This is my website, so do not override; let my WebView load the page

                Log.d("newsUrl",  url);
                newsUrl = url;
                new MyAsynTask().execute();

                return true;
            }


            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

//            Toast.makeText(getActivity(), "page started", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

//            Toast.makeText(getActivity(), "page ended", Toast.LENGTH_SHORT).show();
        }
    }


}
