package com.github.dgarcoe.maskchecker

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class ReferencesActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ReferenceRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_references)
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.references)

        val jsonFileString = getJsonFromAssets(applicationContext, "references.json")

        val gson = Gson()

        val listReferenceType = object : TypeToken<List<Reference>>() {}.type
        var referenceList: List<Reference> = gson.fromJson(jsonFileString, listReferenceType)

        linearLayoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewReferences)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        adapter = ReferenceRecyclerAdapter(referenceList)
        recyclerView.adapter = adapter

    }

    fun getJsonFromAssets(context : Context, filename: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}
