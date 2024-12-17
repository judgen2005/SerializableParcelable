package com.example.serializableparcelable

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var saveButton: Button
    private lateinit var personListView: ListView
    private val personList = mutableListOf<Person>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstNameInput = findViewById(R.id.firstNameInput)
        lastNameInput = findViewById(R.id.lastNameInput)
        addressInput = findViewById(R.id.addressInput)
        phoneInput = findViewById(R.id.phoneInput)
        saveButton = findViewById(R.id.saveButton)
        personListView = findViewById(R.id.personListView)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            personList.map { "${it.firstName} ${it.lastName}" }
        )
        personListView.adapter = adapter
        saveButton.setOnClickListener {
            val firstName = firstNameInput.text.toString().trim()
            val lastName = lastNameInput.text.toString().trim()
            val address = addressInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show()
            } else {
                val person = Person(firstName, lastName, address, phone)
                personList.add(person)
                updateListView()
                clearInputs()
            }
        }
        personListView.setOnItemClickListener { _, _, position, _ ->
            val selectedPerson = personList[position]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(Person::class.java.simpleName, selectedPerson)
            }
            startActivity(intent)
        }
    }

    private fun updateListView() {
        adapter.clear()
        adapter.addAll(personList.map { "${it.firstName} ${it.lastName}" })
        adapter.notifyDataSetChanged()
    }

    private fun clearInputs() {
        firstNameInput.text.clear()
        lastNameInput.text.clear()
        addressInput.text.clear()
        phoneInput.text.clear()
    }
}