package me.lueguo.stickyPackage.entity;

public class UserProto {
    private int length;
    private String content;

    public UserProto(int length, String content) {
        this.length = length;
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public String getContent() {
        return content;
    }
}
