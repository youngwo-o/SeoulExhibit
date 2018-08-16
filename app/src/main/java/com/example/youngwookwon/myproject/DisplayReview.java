package com.example.youngwookwon.myproject;

import android.support.annotation.Keep;

import java.io.Serializable;

/*
    각 전시회 별로 리뷰 데이터 저장
    리뷰 쓴 경우 -> 사용자 아이디 추가
 */
@Keep
public class DisplayReview implements Serializable{
    public int num_rev; //리뷰 개수
    //char[][] Reviews;

    public DisplayReview() {
        num_rev = 0;
       // Reviews = new char[100][];
    }
    void setNum_rev() {num_rev++;}
    /*
    void setReviews(String review) {
        //Reviews[num_rev] = new char[review.length()];
        //Reviews[num_rev] = review.toCharArray();
    }
    */
}
