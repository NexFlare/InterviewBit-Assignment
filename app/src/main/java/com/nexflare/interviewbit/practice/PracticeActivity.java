package com.nexflare.interviewbit.practice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.nexflare.interviewbit.R;
import com.nexflare.interviewbit.crawler.Crawler;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.utility.AnimationUtility;
import com.nexflare.interviewbit.utility.NetworkUtility;

import java.io.IOException;
import java.util.ArrayList;

public class PracticeActivity extends AppCompatActivity implements PracticeMvpView{


    protected PracticeMvpPresenter<PracticeMvpView> mPresenter;

    protected RecyclerView recyclerView;

    protected Crawler crawler;
    protected org.jsoup.nodes.Document document;

    protected ArrayList<PracticeObject> arrayList;
    protected Toolbar toolbar;

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        mPresenter=new PracticePresenter<>();
        mPresenter.onAttach(PracticeActivity.this);
        recyclerView=findViewById(R.id.recyclerPrac);
        crawler=new Crawler(this);

        toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

        Toast.makeText(this, " "+getString(resId), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String message) {

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

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
    public void showListOfItems(ArrayList<PracticeObject> list) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PracticeAdapter(this,list));

        AnimationUtility.animateRecyclerView(recyclerView);
    }

    public class FethchPracticeList extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPostExecute(Void aVoid) {

            arrayList = crawler.getPracticeTracks(document);

            mPresenter.onDataCrawled(arrayList);

            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                document = crawler.getDocument(getString(R.string.prac_url));
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
