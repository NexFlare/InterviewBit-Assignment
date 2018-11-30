package com.nexflare.interviewbit.crawler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.nexflare.interviewbit.R;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.topic.model.TopicObject;
import com.nexflare.interviewbit.tutorial.model.TutorialObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by nexflare on 30/11/18.
 */

public class Crawler {

    private Context context;
    private Document document;


    public Crawler(Context context) {
        this.context = context;
    }

    public Document getDocument(final String url) throws IOException {

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                if(document==null){
                    try {
                        document = Jsoup.connect(context.getString(R.string.base_url)+url).get();
                        Log.d("Crawller", "run: "+context.getString(R.string.base_url)+url);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.run();

        try
        {
            Thread.sleep(700);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }


      //  System.out.println(document.title());
        return document;
    }


    /////////////////////////////////////////////////

    public ArrayList<PracticeObject> getPracticeTracks(Document document){
        ArrayList<PracticeObject> topics=new ArrayList<>();

        Elements panelBody=document.getElementsByClass("panel-body");
        Elements nextPageLinks = null;
        for(Element panel :panelBody){
            nextPageLinks=panel.getElementsByTag("a");
            break;
        }
        // Elements nextPageLinks=panelBody[0].getElementsByTag("a");

        for(Element nextLink:nextPageLinks){
            PracticeObject object=new PracticeObject();
            object.setIconUrl(nextLink.getElementsByClass("course-artwork pull-right").get(0).getElementsByTag("img").attr("src"));
            object.setNextUrl(nextLink.attr("href"));
            object.setTitle(nextLink.getElementsByClass("course-title pull-left").get(0).text());
            //System.out.println("NextLink: " + nextLink.attr("href"));
            //System.out.println("Title: " + nextLink.getElementsByClass("course-title pull-left").get(0).text());
            //System.out.println("ImageSource: " + nextLink.getElementsByClass("course-artwork pull-right").get(0).getElementsByTag("img").attr("src"));

            topics.add(object);
        }



        return  topics;
    }

    /////////////////////////////////////////////////

    public ArrayList<TopicObject> getTopics(Document document){

        ArrayList<TopicObject> topics=new ArrayList<>();

        Elements courseLevels=document.getElementsByClass("topic-box");

        for(Element courseLevel :courseLevels){

            TopicObject object=new TopicObject();
            Element linkCourse=courseLevel.getElementsByTag("a").get(0);
            object.setTitle(linkCourse.getElementsByClass("topic-title").get(0).text());
            object.setNextUrl(linkCourse.attr("href"));
            //System.out.println("NextLink: Topics" + linkCourse.attr("href"));
            //System.out.println("Topics: Topics" + linkCourse.getElementsByClass("topic-title").get(0).text());

            topics.add(object);


        }
        //System.out.println("Topics: Topics" );


        return topics;
    }

    /////////////////////////////////////////////////


    public ArrayList<TutorialObject> getTutorialQues(Document document){

        ArrayList<TutorialObject> list=new ArrayList<>();
        Elements questions=document.getElementsByClass("panel-heading tutorial-heading");

        for(Element question :questions){

            TutorialObject object=new TutorialObject();
            object.setTitle(question.getElementsByClass("slide-title").get(0).text());
            object.setNextUrl(question.getElementsByTag("a").attr("href"));
            // Element linkCourse=.getElementsByTag("a").get(0);
            //System.out.println("Questions" + question.getElementsByClass("slide-title").get(0).text());
            //System.out.println("Next Link" + question.getElementsByTag("a").attr("href"));

            list.add(object);
        }
        //System.out.println("Topics: Topics1" );

        //System.out.println("Topics: Topics2" );

        return list;
    }

    /////////////////////////////////////////////////


    public String getTutorialData(Document document,String id){

        Element tutContent=document.getElementById(id);


        //System.out.println("Topics: Topics" );


        return tutContent.outerHtml();
    }

}


