package com.example.serializableparcelable

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var fullName: TextView
    private lateinit var fullAddress: TextView
    private lateinit var fullPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        fullName = findViewById(R.id.fullName)
        fullAddress = findViewById(R.id.fullAddress)
        fullPhone = findViewById(R.id.fullPhone)
        val person = intent.getParcelableExtra<Person>(Person::class.java.simpleName)
        person?.let {
            fullName.text = "${it.firstName} ${it.lastName}"
            fullAddress.text = it.address
            fullPhone.text = it.phone
        }
    }

    fun onBackButtonClicked(view: View) {
        finish()
    }
}