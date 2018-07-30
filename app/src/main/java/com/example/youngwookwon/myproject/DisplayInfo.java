package com.example.youngwookwon.myproject;

/**
 * Created by user on 2018-07-24.
 */

public class DisplayInfo {
    //
    String code = null; //전시회의 culture code
    String name = null; // 전시회 이름
    String start = null;// 전시회 시작 기간
    String end = null;// 전시회 끝나는 기간
    String place = null; // 전시회 장소
    String image = null; //image를 String형태로
    ///**************이렇게 하면 recycler view(card view) 에 들어가는 최소한의 형태이며, 사진클릭시 전시회 문화코드로 다시 xml파싱함 **********///////////////
    public DisplayInfo(String code, String name, String start, String end, String place, String image){
        this.code = code;
        this.name = name;
        this.start = start;
        this.end = end;
        this.place = place;
        this.image = image;
    }
}
