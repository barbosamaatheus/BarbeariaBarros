package com.example.barbosa.myapplication.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.example.barbosa.myapplication.R;

public class SobreActivity extends AppCompatActivity {

    private WebView webView;
    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Sobre");
        setSupportActionBar(myToolbar);

        this.webView = (WebView) findViewById(R.id.webview);
        String text = "<html><body>"
                + "<p align=\"justify\">"
                + getString(R.string.textoSobre)
                + "</p> "
                + "</body></html>";
        webView.loadData(text, "text/html", "utf-8");
    }
}
