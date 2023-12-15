package com.pinkyp17.heikom;

public class CardModel {
    int couponImage;
    String couponText;
    String couponDesc;


    public CardModel(int couponImage, String couponText, String couponDesc) {
        this.couponImage = couponImage;
        this.couponText = couponText;
        this.couponDesc = couponDesc;
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
}
