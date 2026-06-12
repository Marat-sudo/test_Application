package com.example.myapplication_2


import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication_2.databinding.ActivityMainLayoutTestBinding
import com.example.myapplication_2.utilits.APP_ACTIVITY
import com.example.myapplication_2.utilits.AppStates
import com.example.myapplication_2.utilits.READ_CONTACT
import ui.fragments.ChatFragment
import ui.objects.AppDrawer
import models.CommonModel


class MainChatActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainLayoutTestBinding
    lateinit var mAppDrawer: AppDrawer
    lateinit var mToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_layout_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        APP_ACTIVITY = this

        mBinding = ActivityMainLayoutTestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        initContacts()
        initFields()
        initFunc()

        if (savedInstanceState == null) {
            // Создаем шторку
            mAppDrawer.create()

            // Загружаем стартовый фрагмент чата
            supportFragmentManager.beginTransaction()
                .replace(R.id.dataContainer, ChatFragment())
                .commit()
        }
//        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();


    }

    private fun initContacts() {
//        TODO перенести в файл для работы с бд
        // может это вообще убрать потом
        if (com.example.myapplication_2.utilits.checkPermission(READ_CONTACT))
        {
            val arrayContacts = arrayListOf<CommonModel>()
            val cursor = APP_ACTIVITY.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.let {
                while (it.moveToNext()){
                    val fullName = it.getString(
                        it.getColumnIndexOrThrow(
                            ContactsContract.Contacts.DISPLAY_NAME))

                    val phone = it.getString(
                        it.getColumnIndexOrThrow(
                            ContactsContract.CommonDataKinds.Phone.NUMBER))

                    val newModel = CommonModel()
                    val pars = fullName.split(" ", limit = 2)
                    newModel.firstName = pars[0]
                    newModel.lastName = pars.getOrNull(1) ?: ""
                    newModel.phone = phone.replace(Regex("[\\s,-]"), "")
                    arrayContacts.add(newModel)
                    println(newModel.phone)

                }
            }

            cursor?.close()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACT) == PackageManager.PERMISSION_GRANTED)
        {
            initContacts()
        }

    }

    override fun onStart() {
        super.onStart()
        //Toast.makeText(this, "start.", Toast.LENGTH_SHORT).show();
        AppStates.updateState(AppStates.ONLINE)



    }

    override fun onStop() {
        super.onStop()

        AppStates.updateState(AppStates.ONLINE)
    }

    private fun initFunc() {

        setSupportActionBar(mToolBar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatFragment()).commit()
        mAppDrawer.create()

    }



    private fun initFields() {
        mToolBar = mBinding.mainToolBar
        mAppDrawer = AppDrawer()


    }
}