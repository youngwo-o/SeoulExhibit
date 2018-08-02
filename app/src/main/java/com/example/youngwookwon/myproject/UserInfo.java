package com.example.youngwookwon.myproject;

import android.os.Parcel;
import android.os.Parcelable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

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
public class UserInfo implements Parcelable {
    //firebase에서 받아온, 혹은 업데이트시 보내야할 사용자 정보
    private String Name; //사용자 이름
    private String ID; // 사용자 ID
    private String password;// 비번을 꼭 앱 실행시 저장해야하는가? 아직모르겠다 연선아 알아서판단해!!
    private String nickname; // 닉네임, 변경시 보내야함
    private String image; //이미지 정보가 String 형태로.
   // String[] code = new String[20]; // 좋아요 누른 전시회 정보들이 여기에 들어감 최대 20개.
    private int entry; // 이건 좋아요 한 갯수!
    //DisplayInfo[] mydisplay = new DisplayInfo[20];

    public UserInfo() {}
    public UserInfo(String Name, String nickname, String ID, String password)
    {
        this.Name = Name;
        this.nickname = nickname;
        this.ID = ID;
        this.password = password;
    }
    /*
        다음 액티비티로 UserInfo 객체를 넘겨줄 때 필요
    */
    protected UserInfo(Parcel in) {
        Name = in.readString();
        ID = in.readString();
        password = in.readString();
        nickname = in.readString();
        image = in.readString();
        entry = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(ID);
        dest.writeString(password);
        dest.writeString(nickname);
        dest.writeString(image);
        dest.writeInt(entry);
    }

    public void setName(String Name){this.Name = Name;}
    public void setEntry(int entry) {this.entry = entry;}
    /*
    public UserInfo(String name, String ID, String password, String nickname, String image, int entry)
    {
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
        this.entry = entry;
        for(int i=0; i<entry; i++)
        {
            this.code[i] = getLikeDis(i); // firebase에 저장된 전시회code들(좋아요목록)을 순서대로 넣어야됨
            getDisplay(this.mydisplay[i], this.code[i]); //이렇게하면  mydisplay[entry]의 갯수만큼 들어감
        }
    }
    */

    String getName(){
      return this.Name;
    }
    String getID(){
        return this.ID;
    }
    String getPassword(){
        return this.password;
    }
    String getnickname(){
        return this.nickname;
    }
    String getimage(){
        return this.image;
    }



    /*DisplayInfo getDisplay(DisplayInfo tmp, String code){
        String tmpcode=null, tmpname=null, tmpstart=null, tmpend=null, tmpplace=null, tmpimage=null;
        boolean inname = false, instart = false, inend = false, inplace = false, inimage = false;
        String str_url = "http://openapi.seoul.go.kr:8088/766b79726868697336354e79574b51/xml/SearchConcertDetailService/1/5/"+code;
        try{ //culture code 바탕으로 xml파싱
            URL url = new URL(str_url);
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);
            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("TITLE")) {
                            inname = true;
                        }
                        if (parser.getName().equals("STRTDATE")) {
                            instart = true;
                        }
                        if (parser.getName().equals("END_DATE")) {
                            inend = true;
                        }
                        if (parser.getName().equals("PLACE")) {
                            inplace = true;
                        }
                        if (parser.getName().equals("MAIN_IMG")) {
                            inimage = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (inname) {
                            tmpname = parser.getText();
                            inname = false;
                        }
                        if (instart) {
                            tmpstart = parser.getText();
                            instart = false;
                        }
                        if (inend) {
                            tmpend = parser.getText();
                //            DATE = STARTDATE +" ~ " + END_DATE;
                            inend = false;
                        }
                        if(inplace){
                            tmpplace = parser.getText();
                            inplace =false;
                        }
                        if(inimage) {
                            tmpimage = parser.getText();
                            inimage = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
        }
        tmp = new DisplayInfo(tmpcode, tmpname, tmpstart, tmpend, tmpplace, tmpimage);
        return tmp;
    }
*/
/*
    String getLikeDis(int num) // "num"번째 좋아요 전시회의 정보를 리턴
    { //******Firebase에서 num번째 code를 String형태로 받아야함**********/////
   //    return this.code[num];
   // }
   /*
    void goToFireBase()
    {
        databaseReference.child("id").push().setValue(this.ID);
        databaseReference.child("name").push().setValue(this.name);
        //사진은 어케넣음?
    }*/ //뇌피셜코드;
}
/////////////***********************사용법***************************//////////
/////객체 생성 : UserInfo user = new UserInfo(파싱한내용들); //entry는 좋아요누른횟수. 이것만큼 생성자에서 초기화가 이루어짐
/////******추가적으로 필요하거나 잘못된 내용은 연선이가 수정해주길 바람******////////////