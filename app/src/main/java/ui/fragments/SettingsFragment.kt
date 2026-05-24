package ui.fragments


import android.graphics.Bitmap
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication_2.MainChatActivity
import com.example.myapplication_2.R
import com.example.myapplication_2.RegOrLog
import com.example.myapplication_2.utilits.replaceActivity
import com.example.myapplication_2.utilits.replaceFragment


import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import android.graphics.Color

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {


    override fun onResume() {
        super.onResume()

    }

    private fun initFields()
    {

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenuHost()

        val phoneNum: ConstraintLayout = view.findViewById(R.id.setting_btn_change_number_phone)
        val userName: ConstraintLayout = view.findViewById(R.id.setting_btn_change_user_name)
        val info: ConstraintLayout = view.findViewById(R.id.setting_btn_change_bio)

        val photo: de.hdodenhof.circleimageview.CircleImageView = view.findViewById(R.id.setting_change_photo)


        phoneNum.setOnClickListener {

        }

        userName.setOnClickListener {
            replaceFragment(ChangeUserNameFragment())
        }

        info.setOnClickListener {
            replaceFragment(ChangeInfoFragment())
        }

        photo.setOnClickListener {
            changePhoto()
        }

    }



    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // Успешно! Получаем URI обрезанного изображения
            val uriContent = result.uriContent
            // val croppedImageFilePath = result.getUriFilePath(this)

            // Например, отображаем в ImageView или отправляем на сервер:
            // binding.myImageView.setImageURI(uriContent)
        } else {
            // An error occurred.
            val exception = result.error
            // Handle the error.
        }
    }

    private fun startCrop() {

        // Start cropping activity for a pre-acquired image with custom settings.
        cropImage.launch(
            CropImageContractOptions(
                uri = null,
                cropImageOptions = CropImageOptions(
                    guidelines = CropImageView.Guidelines.ON,
                    outputCompressFormat = Bitmap.CompressFormat.PNG
                )
            )
        )
        }


    private fun changePhoto() {
        cropImage.launch(
            CropImageContractOptions(
                uri = null, // null — библиотека сама откроет выбор: Камера / Галерея
                cropImageOptions = CropImageOptions().apply {
                    // Включаем сетку-направляющие (как у вас в примере)


                    cropShape = CropImageView.CropShape.OVAL
                    fixAspectRatio = true
                    aspectRatioX = 1
                    aspectRatioY = 1
                    guidelines = CropImageView.Guidelines.OFF
                    allowFlipping=true
                    allowRotation=true



                    activityBackgroundColor = Color.BLACK


                    showProgressBar=true
                    activityTitle = "Редактирование"         // Заголовок на верхней панели
                    activityMenuIconColor = Color.WHITE     // Цвет иконок в меню
                    toolbarBackButtonColor = Color.WHITE   // Цвет стрелки "Назад"
                    toolbarColor = Color.WHITE              // Цвет самого Тулбара (верхней панели)

                }
            )
        )
    }

    private fun initMenuHost() {
        val menuHost: MenuHost = requireActivity()

        // 2. Добавляем провайдер меню
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Раздуваем ваше меню здесь
                menuInflater.inflate(R.menu.settings_action_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Обрабатываем нажатия
                when (menuItem.itemId) {
                    R.id.setting_menu_change_info -> replaceFragment(ChangeInfoFragment())

                    //R.id.setting_menu_change_photo -> pass

                    R.id.setting_menu_exit -> (activity as MainChatActivity).replaceActivity(RegOrLog())


                }
                return true

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // ^ Важно: передаем viewLifecycleOwner, чтобы меню само удалялось
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_menu_exit -> {
                (activity as MainChatActivity).replaceActivity(RegOrLog())
            }
            R.id.setting_menu_change_info -> replaceFragment(ChangeInfoFragment())

        }

        return true
    }
}

