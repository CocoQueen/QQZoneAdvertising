package com.example.coco.qqzoneadertising;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerview设置适配器绑定数据
        mRv = findViewById(R.id.mRv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(new MyAdapter());
    }
}
