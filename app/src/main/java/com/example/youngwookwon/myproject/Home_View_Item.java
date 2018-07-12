package com.example.youngwookwon.myproject;

/**
 * Created by user on 2018-07-07.
 */

public class Home_View_Item {
        String image;
        String title;
        String date;
        String place;
        String code;


        String getImage(){
            return this.image;
        }
        String getTitle(){
            return this.title;
        }
        String getDate() { return this.date;}
        String getPlace() { return this.place;}
        String getCode(){
            return this.code;
        }

        Home_View_Item(String image, String title, String date, String place, String code){
            this.image=image;
            this.title=title;
            this.date = date;
            this.place = place;
            this.code = code;
        }
}
