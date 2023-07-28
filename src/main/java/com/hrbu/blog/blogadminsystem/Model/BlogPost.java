package com.hrbu.blog.blogadminsystem.Model;

public class BlogPost {
    private String uid;
    private String name;
    private String author;
    private String content;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BlogPost{\n" +
                "uid : " + uid + '\n' +
                "name : " + name + '\n' +
                "author : " + author + '\n' +
                "---------- content ----------\n" + content +
                "\n-----------------------------\n}\n";
    }
}
