package com.example.myapplication.View;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicActivity;

//  Activity를 상속하며 Contract.View를 구현하는 BasicActivity를 상속
public class ItemListActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_itemlist);
    }
}
