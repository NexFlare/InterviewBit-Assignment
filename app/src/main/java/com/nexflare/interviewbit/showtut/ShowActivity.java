package com.nexflare.interviewbit.showtut;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nexflare.interviewbit.R;
import com.nexflare.interviewbit.crawler.Crawler;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.utility.NetworkUtility;

import java.io.IOException;
import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity implements ShowMvpView {


    protected ShowMvpPresenter<ShowMvpView> mPresenter;

    protected  RecyclerView recyclerView;

    protected Crawler crawler;
    protected org.jsoup.nodes.Document document;

    protected String htmlOfPage,url,id;

    protected ArrayList<PracticeObject> arrayList;
    protected Toolbar toolbar;

    protected WebView webview;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";

    protected ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        url = getIntent().getStringExtra("url");

        String len[]=url.split("/");
        id=len[len.length-1];

        webview=findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setMediaPlaybackRequiresUserGesture(false);


        mPresenter=new ShowPresenter<>(this);
        mPresenter.onAttach(ShowActivity.this);
        recyclerView=findViewById(R.id.recyclerPrac);
        crawler=new Crawler(this);

        /*toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        progressDialog=new ProgressDialog(this);

        mPresenter.onCardExhausted();
    }

    @Override
    public void showLoading() {
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();

    }

    @Override
    public void hideLoading() {

        progressDialog.dismiss();

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtility.checknewWork();
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void crawlPracticeList() {

        FethchPracticeList fethchPracticeList=new FethchPracticeList();

        fethchPracticeList.execute();

    }

    @Override
    public void showInformationOnPage(String html) {

          html="<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + "\n"+html;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                webview.loadDataWithBaseURL("", html, mimeType, encoding, "");
                //textVeiw.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
            } else {
                webview.loadDataWithBaseURL("", html, mimeType, encoding, "");
                //textVeiw.setText(Html.fromHtml(html));
            }

    }

    public class FethchPracticeList extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPostExecute(Void aVoid) {

            htmlOfPage = crawler.getTutorialData(document,id);

            mPresenter.onDataCrawled(htmlOfPage);

            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                document = crawler.getDocument(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
