package com.blog.util.Enums;

public enum LikeableEnum {
    ARTICLE("Article"),
    COMMENT("Comment"),
    REPLY("Reply"),
    LMESSAGE("Lmessage");

    private final String value;

    LikeableEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}