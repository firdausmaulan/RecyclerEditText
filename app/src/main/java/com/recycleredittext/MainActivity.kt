package com.recycleredittext

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnVertical.setOnClickListener {
            startActivity(Intent(this, VerticalActivity::class.java))
        }

        btnHorizontal.setOnClickListener {
            Toast.makeText(this, "Still not work!" +
                    "\nIt's really hard to manage edit text on horizontal recyclerview." +
                    "\nTry to use custom dialog with edit text instead of put edit text on horizontal recyclerview",
                    Toast.LENGTH_LONG).show()
            //startActivity(Intent(this, HorizontalActivity::class.java))
        }

    }
}
