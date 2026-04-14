package com.example.myapplication_2.utilits

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication_2.R

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.dataContainer,
            fragment
        ).commit()
}