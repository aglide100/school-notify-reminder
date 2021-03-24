package com.example.myapplication.Model;

public class Post {
    public Academic GetAcademic() {
        Academic newPost = null;
        return newPost;
    }

    public Announce GetAnnounce() {
        Announce newPost = null;
        return newPost;
    }

    public Scholarship GetScholarship() {
        Scholarship newPost = null;
        return newPost;
    }

    public Event GetEvent() {
        Event newPost = null;
        return newPost;
    }

    public Bid GetBid() {
        Bid newPost = null;
        return newPost;
    }

    public Recruitment GetRecruitment() {
        Recruitment newPost = null;
        return newPost;
    }


}

class PostBone {
    String title;
    String Status;
    String uri;
    String date;
    int num;
}

// 공지
class Announce {
    String code = "MN2000191";
    PostBone content;
}

// 장학
class Scholarship {
    String code = "MN2000195";
    PostBone content;
}

// 학사
class Academic {
    String code = "MN2000194";
    PostBone content;
}

// 행사
class Event {
    String code = "MN2000198";
    PostBone content;
}

// 입찰
class Bid {
    String code = "MN2000196";
    PostBone content;
}


// 모집
class Recruitment {
    String code = "MN2000197";
    PostBone content;
}