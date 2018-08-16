package com.example.youngwookwon.myproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

import android.support.annotation.Keep;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by user on 2018-07-24.
 */

/*
* Firebase DB
 * 배열형태 저장 안됨 ->List로 가능한듯
 * DB child 이름 : 닉네임등으로 해야함
  * Firebase Database paths must not contain '.', '#', '$', '[', or ']')
*/
@Keep
public class UserInfo implements Serializable {
    //firebase에서 받아온, 혹은 업데이트시 보내야할 사용자 정보
    private String Name; //사용자 이름
    private String Id; // 사용자 ID
    private String Password;
    private String Nickname; // 닉네임, 변경시 보내야함
    private String image; //이미지 정보가 String 형태로.
   // String[] code = new String[20]; // 좋아요 누른 전시회 정보들이 여기에 들어감 최대 20개.
    private int entry; //좋아요 한 갯수
    // DisplayInfo[] mydisplay = new DisplayInfo[20];

    public UserInfo() {}
    public UserInfo(String Name, String nickname, String ID, String password)
    {
        this.Name = Name;
        this.Nickname = nickname;
        this.Id = ID;
        this.Password = password;
    }

    public void setName(String Name){this.Name = Name;}
    public void setNickname(String Nickname) {this.Nickname = Nickname;}
    public void setId(String ID) {this.Id = ID;}
    public void setPassword(String Password) {this.Password = Password;}
    public void setEntry(int entry) {this.entry = entry;}

    public String getName(){
      return this.Name;
    }
    public String getId(){
        return this.Id;
    }
    public String getPassword(){
        return this.Password;
    }
    public String getNickname(){
        return this.Nickname;
    }
    public String getImage(){
        return this.image;
    }
}
/////////////***********************사용법***************************//////////
/////객체 생성 : UserInfo user = new UserInfo(파싱한내용들); //entry는 좋아요누른횟수. 이것만큼 생성자에서 초기화가 이루어짐
/////******추가적으로 필요하거나 잘못된 내용은 연선이가 수정해주길 바람******////////////