package com.pinkyp17.heikom;

public class CardModel {
    int couponImage;
    String couponText;
    String couponDesc;



    //points
    int points;


    public CardModel(int couponImage, String couponText, String couponDesc,int points) {
        this.couponImage = couponImage;
        this.couponText = couponText;
        this.couponDesc = couponDesc;
        this.points = points;
    }

    public int getCouponImage() {
        return couponImage;
    }

    public String getCouponText() {
        return couponText;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public int getPoints() {
        return points;
    }
}
