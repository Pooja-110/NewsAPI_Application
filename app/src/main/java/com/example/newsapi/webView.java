package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.airbnb.lottie.LottieAnimationView;

public class webView extends AppCompatActivity {
    WebView webView;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webpage);
        animationView = findViewById(R.id.anim);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView.setWebViewClient(new WebViewClient());
        animationView.setAnimation(R.raw.shareicon);
        animationView.playAnimation();

         animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareWebPage(url);
            }
        });

        assert url != null;
        webView.loadUrl(url);
    }


    private void shareWebPage(String url) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(shareIntent, "Share this page via"));
    }
}
