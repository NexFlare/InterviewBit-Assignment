package com.nexflare.interviewbit.tutorial;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.nexflare.interviewbit.R;
import com.nexflare.interviewbit.crawler.Crawler;
import com.nexflare.interviewbit.tutorial.model.TutorialObject;
import com.nexflare.interviewbit.utility.AnimationUtility;
import com.nexflare.interviewbit.utility.NetworkUtility;

import java.io.IOException;
import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity implements TutorialMvpView {


    protected TutorialMvpPresenter<TutorialMvpView> mPresenter;

    protected RecyclerView recyclerView;

    protected Crawler crawler;
    protected org.jsoup.nodes.Document document;

    protected ArrayList<TutorialObject> arrayList;

    protected String url;

    protected Toolbar toolbar;

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);


        url = getIntent().getStringExtra("url");

        mPresenter=new TutorialPresenter<>(this);
        mPresenter.onAttach(TutorialActivity.this);
        recyclerView=findViewById(R.id.recyclerPrac);
        crawler=new Crawler(this);

        toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog =new ProgressDialog(this);


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
    public void showListOfItems(ArrayList<TutorialObject> list) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TutorialAdapter(this,list));
        AnimationUtility.animateRecyclerView(recyclerView);


    }

    public class FethchPracticeList extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPostExecute(Void aVoid) {

            arrayList = crawler.getTutorialQues(document);

            mPresenter.onDataCrawled(arrayList);

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
        return true;
    }
}
