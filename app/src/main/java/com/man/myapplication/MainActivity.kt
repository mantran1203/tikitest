package com.man.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.man.myapplication.adapter.KeyWordAdapter
import com.man.myapplication.delegate.KeyWordDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), KeyWordDelegate {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: KeyWordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        initViewModel()
    }

    private fun initAdapter() {
        adapter = KeyWordAdapter(this)
        rv_keyword.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getKeywords()
            .observe(this, Observer<List<String>> { data -> adapter.updateData(data) })
        viewModel.getError()
            .observe(this, Observer<String> { err -> showMsg(err) })
    }

    override fun onKeyWordSelected(keyword: String) {
        showMsg(keyword)
    }

    private fun showMsg(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
