package com.lst_1995.main

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.domain.usecase.Theme
import com.lst_1995.main.databinding.ActivityFeatureMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeatureMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeatureMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var isPermissionRequested = false

    private val permissions =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (permissionCheck()) {
            startMainFlow()
        } else {
            requestPermission()
        }
    }

    private fun startMainFlow() {
        // 스플래시 화면 설치 및 권한이 허용된 경우만 진행
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        setBinding()
        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            splashScreen.setKeepOnScreenCondition { false }
            themeCheck()
            modeCheck()
        }
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feature_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun permissionCheck(): Boolean = permissions.all { permissionIsPossible(it) }

    private fun requestPermission() {
        // 만약 사용자가 "다시 묻지 않음"을 선택했는지 확인
        if (shouldShowPermissionRationale()) {
            showPermissionDeniedDialogWithSettings()
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_CODE)
        }
    }

    // 사용자가 "다시 묻지 않음"을 선택했는지 확인하는 함수
    private fun shouldShowPermissionRationale(): Boolean = permissions.any { ActivityCompat.shouldShowRequestPermissionRationale(this, it) }

    private fun permissionIsPossible(permission: String): Boolean =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // 권한이 허용되면 메인 플로우로 진입
                startMainFlow()
            } else {
                // 권한 거부 시 다시 권한 요청 또는 설정으로 이동
                if (shouldShowPermissionRationale()) {
                    showPermissionDeniedDialogWithSettings()
                } else {
                    showPermissionDeniedDialog()
                }
            }
        }
    }

    // 권한이 거부된 경우 설정 화면으로 안내하는 다이얼로그
    private fun showPermissionDeniedDialogWithSettings() {
        MaterialAlertDialogBuilder(this)
            .setTitle("권한이 필요합니다")
            .setMessage("이 앱을 사용하려면 권한을 허용해야 합니다. 설정으로 이동하여 권한을 수동으로 허용하세요.")
            .setPositiveButton("설정으로 이동") { _, _ ->
                // 앱의 설정 화면으로 이동하여 권한을 허용하도록 유도
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = android.net.Uri.fromParts("package", packageName, null)
                    }
                startActivity(intent)
                finish()
            }.setNegativeButton("취소") { _, _ ->
                finish()
            }.show()
    }

    private fun showPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("권한 허용")
            .setMessage("앱을 사용하기 위해 권한을 허용해주세요.")
            .setPositiveButton("확인") { _, _ ->
                requestPermission()
            }.setNegativeButton("취소") { _, _ ->
                finish()
            }.show()
    }

    private suspend fun themeCheck() {
        viewModel.theme.first().let { theme ->
            when (theme) {
                Theme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Theme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Theme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    private suspend fun modeCheck() {
        viewModel.mode.collect { mode ->
            val intent = Intent()
            when (mode) {
                ModeType.NONE.name -> {
                    intent.component =
                        ComponentName(
                            "com.lst_1995.freetableorder",
                            "com.lst_1995.login.LoginActivity",
                        )
                }

                ModeType.MANAGE.name -> {
                    intent.component =
                        ComponentName(
                            "com.lst_1995.freetableorder",
                            "com.lst_1995.manage.ManageMainActivity",
                        )
                }

                ModeType.KITCHEN.name -> {
                    intent.component =
                        ComponentName(
                            "com.lst_1995.freetableorder",
                            "com.lst_1995.kitchen.KitchenMainActivity",
                        )
                }

                ModeType.TABLE.name -> {
                    intent.component =
                        ComponentName(
                            "com.lst_1995.freetableorder",
                            "com.lst_1995.table.TableMainActivity",
                        )
                }
            }
            startActivity(intent)
            finish()
        }
    }

    companion object {
        private const val SPLASH_SCREEN_DURATION = 1000L
        private const val REQUEST_PERMISSION_CODE = 200
    }
}
