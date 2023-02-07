package com.zzt.rvadapter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzt.rvadapter.databinding.ActivitySimapleMainBinding
import java.util.*

class SimapleMainActivity : AppCompatActivity() {
    var data: List<DelegateMultiEntity>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySimapleMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DelegateMultiAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        data = getDelegateMultiItemData()
        adapter.setList(data)
    }

    fun getDelegateMultiItemData(): List<DelegateMultiEntity> {
        val list: MutableList<DelegateMultiEntity> = ArrayList()
        for (i in 0..40) {
            list.add(DelegateMultiEntity())
        }
        return list
    }


    override fun onStop() {
        super.onStop()

        Log.d("Tag", "这里在保存数据  获取：" + data.toString())

    }


}