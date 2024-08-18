package com.blog.util.Enums;

public enum LikeableEnum {
    Article("Article"),
    Comment("Comment"),
    Reply("Reply"),
    Lmessage("Lmessage");

    private final String value;

    LikeableEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}