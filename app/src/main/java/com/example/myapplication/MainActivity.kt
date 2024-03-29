package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ContactsAdapter
import com.example.myapplication.model.Contacts
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    private val mRecyclerView: RecyclerView? = null
    private val viewItems: ArrayList<Contacts> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val _recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        _recyclerView.setHasFixedSize(true)
        _recyclerView.setLayoutManager(GridLayoutManager(this, 2))
//        _recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val items = ArrayList<Contacts>()

        //adding some dummy data to the list
//        items.add(Contacts("1", "monroe", "", "", ""))
//        items.add(Contacts("2", "data", "", "", ""))
//        items.add(Contacts("3", "one", "", "", ""))
//        items.add(Contacts("4", "first", "", "", ""))
//        items.add(Contacts("5", "tes", "", "", ""))

        val adapter = ContactsAdapter(viewItems, this)

        _recyclerView.adapter = adapter

        addItemsFromJSON();
    }
    fun addItemsFromJSON() {
        try {
            val jsonDataString: String = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val id = itemObj.getString("id")
                val firstName = itemObj.getString("firstName")
                val lastName = itemObj.getString("lastName")
                val email = itemObj.getString("email")
                val dob = itemObj.getString("dob")
                val contacts = Contacts(id, firstName, lastName,email, dob)
                viewItems.add(contacts)
            }
        } catch (e: JSONException) {
        } catch (e: IOException) {
        }
    }
    @Throws(IOException::class)
    fun readJSONDataFromFile(): String{

        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try{
            var jsonString: String? = null
            inputStream = resources.openRawResource(R.raw.data)
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }

        }finally {
            inputStream?.close()
        }
        return String(builder)
    }

}
