package ui.fragments


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
import com.example.myapplication_2.R
import com.example.myapplication_2.RegOrLog
import com.example.myapplication_2.utilits.replaceActivity
import com.example.myapplication_2.utilits.replaceFragment


import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import models.UserCache
import java.io.File
import java.io.FileOutputStream
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.myapplication_2.utilits.APP_ACTIVITY
import models.UserDatabase.saveUser


class SettingsFragmnt : BaseFragment(R.layout.fragment_settings) {
    private val TAG = "M_DEBUG"
    private lateinit var mBinding: de.hdodenhof.circleimageview.CircleImageView
    private lateinit var phoneNumView: TextView
    private lateinit var userNameView: TextView
    private lateinit var userBioView: TextView
    private lateinit var userFullName: TextView
    private lateinit var userBackground: ImageView

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Этот блок выполнится, когда пользователь выберет картинку
        if (uri != null) {

            saveUserBgUrl(uri)
            saveUser(UserCache.currentUser)

        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // Успешно! Получаем URI обрезанного изображения
            val uriContent = result.uriContent
            // val croppedImageFilePath = result.getUriFilePath(this)


            savePhotoUrl(uriContent)

            saveUser(UserCache.currentUser)
        }
        else {
            // An error occurred.
            val exception = result.error
            // Handle the error.
        }
    }

    private fun savePhotoUrl(uri: Uri?) {
        try {
            val uri = uri ?: return
            // Открываем поток чтения из временного файла библиотеки
            val inputStream = requireContext().contentResolver.openInputStream(uri)

            // Создаем постоянный файл в локальной папке приложения
            val destinationFile = File(requireContext().filesDir, "user_profile_bg.jpg")
            val outputStream = FileOutputStream(destinationFile)

            // Копируем байты из временного файла в постоянный
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            // ВОТ ОН — ваш постоянный локальный URL/путь к файлу
            val permanentPhotoPath = destinationFile.absolutePath

            UserCache.currentUser?.photoUrl = permanentPhotoPath

            Log.d(TAG, "------------------------------")
            Log.d(TAG, permanentPhotoPath)

            setPhoto()
            APP_ACTIVITY.mAppDrawer.updateHeader()


            // Теперь записываем permanentPhotoPath в вашу базу данных (SQLite / Room)
            //savePathToDatabase(permanentPhotoPath)


        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
    private fun saveUserBgUrl(uri: Uri?) {
        try {
            val sourceUri = uri ?: return

            // Открываем поток чтения из временного файла библиотеки
            val inputStream = requireContext().contentResolver.openInputStream(sourceUri)

            // Создаем постоянный файл в локальной папке приложения
            val destinationFile = File(requireContext().filesDir, "user_profile_avatar.jpg")
            val outputStream = FileOutputStream(destinationFile)

            // Копируем байты из временного файла в постоянный
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            // ВОТ ОН — ваш постоянный локальный URL/путь к файлу
            val permanentPhotoPath = destinationFile.absolutePath

            UserCache.currentUser?.userBg = permanentPhotoPath

            setUserBg()
            APP_ACTIVITY.mAppDrawer.updateHeader()



        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    override fun onResume() {
        super.onResume()


    }

    private fun initFields()
    {

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenuHost()



        mBinding = view.findViewById(R.id.settingUserPhoto)
        phoneNumView = view.findViewById(R.id.setting_phone_number)
        userNameView = view.findViewById(R.id.setting_user_name)
        userBioView = view.findViewById(R.id.setting_bio)
        userFullName = view.findViewById(R.id.setting_user_full_name)

        userBackground = view.findViewById(R.id.UserBackground)

        /*
    setting_phone_number
    setting_user_name
    setting_bio*/

        val phoneNum: ConstraintLayout = view.findViewById(R.id.setting_btn_change_number_phone)
        val userName: ConstraintLayout = view.findViewById(R.id.setting_btn_change_user_name)
        val info: ConstraintLayout = view.findViewById(R.id.setting_btn_change_bio)
        val status: TextView = view.findViewById(R.id.settings_status)

        status.text = UserCache.currentUser?.state.toString()


        val photo: de.hdodenhof.circleimageview.CircleImageView = view.findViewById(R.id.setting_change_photo)

        setPhoto()
        initUserInfo()
        setUserBg()

        phoneNum.setOnClickListener {

        }

        userName.setOnClickListener {
            replaceFragment(ChangeUserNameFragment())
            initUserInfo()
        }

        info.setOnClickListener {
            replaceFragment(ChangeInfoFragment())
            initUserInfo()
        }


        photo.setOnClickListener {
            changePhoto()
        }

    }


    private fun initUserInfo(){
        /*
        setting_phone_number
        setting_user_name
        setting_bio*/
        val fullName: String = UserCache.currentUser?.firstName + " " + UserCache.currentUser?.lastName
        userFullName.text = fullName
        phoneNumView.text = UserCache.currentUser?.phone
        userNameView.text = UserCache.currentUser?.userName
        userBioView.text = UserCache.currentUser?.bio

        saveUser(UserCache.currentUser)

    }

    private fun setPhoto() {

        val urlPhoto = UserCache.currentUser?.photoUrl

        if (urlPhoto != null && urlPhoto.isNotEmpty()) {

            // Glide сам поймет, что это локальный путь, проверит файл и загрузит его
            Glide.with(this) // передаем контекст фрагмента или активити
                .load(urlPhoto)
                .signature(ObjectKey(File(urlPhoto).lastModified()))
                .placeholder(R.drawable.sticker) // дефолтная картинка, пока грузится фото
                .error(R.drawable.sticker)       // картинка, если произошла ошибка
                .into(mBinding) // ваш CircleImageView
        }
    }

    private fun setUserBg() {

        val urlBg = UserCache.currentUser?.userBg
        println(urlBg)
        println(urlBg != null && urlBg.isNotEmpty())
        if (urlBg != null && urlBg.isNotEmpty()) {
            Glide.with(this)
                .load(urlBg)
                .signature(ObjectKey(File(urlBg).lastModified()))
                .placeholder(R.drawable.sticker) // дефолтная картинка, пока грузится фото
                .error(R.drawable.sticker)
                .into(userBackground)
        }
    }



    private fun changePhoto() {

        cropImage.launch(
            CropImageContractOptions(
                uri = null, // null — библиотека сама откроет выбор: Камера / Галерея
                cropImageOptions  = CropImageOptions(

                ).apply {

                    cropShape = CropImageView.CropShape.OVAL
                    fixAspectRatio = true
                    aspectRatioX = 1
                    aspectRatioY = 1
                    guidelines = CropImageView.Guidelines.OFF
                    allowFlipping=true
                    allowRotation=true

                    activityBackgroundColor = Color.GRAY




                    showProgressBar=true
                    activityTitle = "Редактирование"         // Заголовок на верхней панели
                    activityMenuIconColor = Color.WHITE     // Цвет иконок в меню
                    toolbarBackButtonColor = Color.WHITE   // Цвет стрелки "Назад"
                    toolbarColor = Color.BLACK              // Цвет самого Тулбара (верхней панели)

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
                    R.id.setting_menu_change_photo -> changePhoto()
                    R.id.setting_menu_change_info -> replaceFragment(ChangeInfoFragment())
                    R.id.setting_menu_change_background -> pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    //R.id.setting_menu_change_photo -> pass

                    R.id.setting_menu_exit -> APP_ACTIVITY.replaceActivity(RegOrLog())

                    else -> return false
                }


                return true

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // ^ Важно: передаем viewLifecycleOwner, чтобы меню само удалялось
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_menu_exit ->  APP_ACTIVITY.replaceActivity(RegOrLog())

            R.id.setting_menu_change_info -> replaceFragment(ChangeInfoFragment())

        }

        return true
    }

}



