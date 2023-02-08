package com.zzt.rvadapterhelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zzt.rvadapterhelp.widget.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_test, rv_test2, rv_test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rv_test = findViewById(R.id.rv_test);
        rv_test.setLayoutManager(new LinearLayoutManager(this));

        AAdapter aAdapter = new AAdapter( getList("1"));
        rv_test.setAdapter(aAdapter);


        rv_test2 = findViewById(R.id.rv_test2);
        rv_test2.setLayoutManager(new LinearLayoutManager(this));
        ABAdapter bAdapter = new ABAdapter(getList("2"));
        rv_test2.setAdapter(bAdapter);

//        View headerView = LayoutInflater.from(this).inflate(R.layout.rv_item_header, rv_test2, false);
//        bAdapter.addHeaderView(headerView);
//
//        View footView = LayoutInflater.from(this).inflate(R.layout.rv_item_foot , rv_test2, false);
//        bAdapter.addFooterView(footView);

        rv_test3 = findViewById(R.id.rv_test3);
        rv_test3.setLayoutManager(new LinearLayoutManager(this));
        CAdapter cAdapter = new CAdapter(  getList("3"));
        rv_test3.setAdapter(cAdapter);
    }


    public List<AEntity> getList(String tag) {
        List<AEntity> mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add(new AEntity("列表 " + tag + " i:" + i));
        }
        return mList;
    }

}