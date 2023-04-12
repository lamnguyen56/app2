package com.example.app2

//import androidx.appcompat.widget.SearchView
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class speciesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<dataSpecies>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: myAdapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<dataSpecies>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)



        imageList = arrayOf(
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong)
        titleList = arrayOf(
            "ListView",
            "CheckBox",
            "ImageView",
            "Toggle Switch",
            "Date Picker",
            "Rating Bar",
            "Time Picker",
            "TextView",
            "EditText",
            "Camera")
        descList = arrayOf(
            getString(R.string.listview),
            getString(R.string.checkbox),
            getString(R.string.imageview),
            getString(R.string.toggle),
            getString(R.string.date),
            getString(R.string.rating),
            getString(R.string.time),
            getString(R.string.textview),
            getString(R.string.edit),
            getString(R.string.camera))
        detailImageList = arrayOf(
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm,
            R.drawable.xuongrong,
            R.drawable.nhadamm)
        recyclerView = findViewById(R.id.recycleView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<dataSpecies>()
        searchList = arrayListOf<dataSpecies>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        myAdapter = myAdapter(searchList)
        recyclerView.adapter = myAdapter
        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

    }

    private fun getData(){
        for (i in imageList.indices){
            val dataClass = dataSpecies(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = myAdapter(searchList)
    }
}