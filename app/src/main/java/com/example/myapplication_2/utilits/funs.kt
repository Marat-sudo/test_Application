package com.example.myapplication_2.utilits

import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication_2.R
import com.squareup.picasso.Picasso

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addStack:Boolean = true){
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.data_Container,
                fragment
            ).commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.data_Container,
                fragment
            ).commit()
    }

}

fun Fragment.replaceFragment(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.data_Container,
            fragment
        ).commit()
}

fun ImageView.downloadAndSetImage(url: String){
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.drawable.m)
        .into(this)
}

