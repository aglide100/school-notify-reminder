package com.example.myapplication.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Post extends RealmObject {
    @PrimaryKey
    String id;

}
