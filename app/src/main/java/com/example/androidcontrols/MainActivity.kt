package com.example.androidcontrols

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageButton = findViewById<Button>(R.id.imageButton)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val switchControl = findViewById<Switch>(R.id.switchControl)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val seekValueText = findViewById<TextView>(R.id.seekValueText)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val progressButton = findViewById<Button>(R.id.progressButton)
        val alertButton = findViewById<Button>(R.id.alertButton)
        val statusText = findViewById<TextView>(R.id.statusText)

        submitButton.setOnClickListener {
            val input = editText.text.toString().trim()
            if (input.isEmpty()) {
                statusText.text = "Status: Please enter text in EditText"
                Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show()
            } else {
                statusText.text = "Status: EditText value -> $input"
                Toast.makeText(this, "Submitted: $input", Toast.LENGTH_SHORT).show()
            }
        }

        imageButton.setOnClickListener {
            imageView.setImageResource(android.R.drawable.ic_menu_compass)
            statusText.text = "Status: ImageView updated"
            Toast.makeText(this, "Image changed", Toast.LENGTH_SHORT).show()
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            statusText.text = "Status: CheckBox -> ${if (isChecked) "Checked" else "Unchecked"}"
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selected = when (checkedId) {
                R.id.radioOne -> "Option 1"
                R.id.radioTwo -> "Option 2"
                R.id.radioThree -> "Option 3"
                else -> "None"
            }
            statusText.text = "Status: RadioButton selected -> $selected"
        }

        val spinnerItems = listOf("Select an option", "Android", "Kotlin", "Flutter", "React Native")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = spinnerItems[position]
                statusText.text = "Status: Spinner selected -> $selectedItem"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                statusText.text = "Status: Nothing selected from Spinner"
            }
        }

        switchControl.setOnCheckedChangeListener { _, isChecked ->
            statusText.text = "Status: Switch -> ${if (isChecked) "ON" else "OFF"}"
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekValueText.text = "SeekBar value: $progress"
                statusText.text = "Status: SeekBar -> $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        progressButton.setOnClickListener {
            var progressValue = 0
            val handler = Handler(Looper.getMainLooper())

            val runnable = object : Runnable {
                override fun run() {
                    if (progressValue <= 100) {
                        progressBar.progress = progressValue
                        progressValue += 10
                        statusText.text = "Status: ProgressBar -> $progressValue%"
                        if (progressValue <= 100) {
                            handler.postDelayed(this, 200)
                        } else {
                            statusText.text = "Status: ProgressBar completed"
                        }
                    }
                }
            }

            progressBar.progress = 0
            runnable.run()
        }

        alertButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Android Controls Demo")
                .setMessage("This is an AlertDialog example from Android Studio.")
                .setPositiveButton("OK") { _, _ ->
                    statusText.text = "Status: AlertDialog dismissed"
                }
                .create()
                .show()
        }
    }
}
