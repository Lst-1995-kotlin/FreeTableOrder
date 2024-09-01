package com.lst_1995.main

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.main.databinding.ActivityFeatureMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeatureMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeatureMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feature_main)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        // 앱 테마 체크
        themeCheck()
        // 실행할 모드 확인하여 해당 모드 activity 실행
        modeCheck()
    }

    private fun themeCheck() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.themeMode.collect { theme ->
                    AppCompatDelegate.setDefaultNightMode(theme)
                }
            }
        }
    }

    private fun modeCheck() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectMode.collect { mode ->
                    val intent = Intent()
                    when (mode) {
                        ModeType.NONE.name -> {
                            intent.component =
                                ComponentName(
                                    "com.lst_1995.freetableorder",
                                    "com.lst_1995.main.FeatureMainActivity"
                                )
                        }
                        ModeType.MANAGE.name -> {

                        }
                        ModeType.KITCHEN.name -> {

                        }
                        ModeType.TABLE.name -> {

                        }
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
