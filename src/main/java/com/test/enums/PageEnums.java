package com.test.enums;

public enum PageEnums {
    NEWS_PAGE("/tone/news"),
    GOOGLE("https://www.google.co.uk");

    String pageEnum;

    PageEnums(String pageEnum) {
        this.pageEnum = pageEnum;
    }

    public String getPageUlr() {
        return pageEnum;
    }
}
