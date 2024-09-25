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
import com.lst_1995.core.domain.usecase.Theme
import com.lst_1995.main.databinding.ActivityFeatureMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
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
        startCheck()
    }

    private fun startCheck() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { themeCheck() }
                launch { modeCheck() }
            }
        }
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
}
