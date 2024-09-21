package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.SettingRepository
import com.lst_1995.core.domain.util.NetworkManager
import javax.inject.Inject

enum class PasswordErrorType {
    CHANGE_BLANK,
    CHANGE_CHECK_BLANK,
    NOT_MATCH,
    LENGTH,
    NEWLINE,
    SPACE,
    NETWORK,
    NONE,
}

class SettingUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
        private val networkManager: NetworkManager,
    ) {
        suspend fun setTablePassword(password: String) = settingRepository.setTablePassword(password)

        fun passwordCheck(
            password: String?,
            passwordCheck: String?,
        ): PasswordErrorType =
            when {
                password.isNullOrEmpty() -> PasswordErrorType.CHANGE_BLANK
                passwordCheck.isNullOrEmpty() -> PasswordErrorType.CHANGE_CHECK_BLANK
                password != passwordCheck -> PasswordErrorType.NOT_MATCH
                password.length < 4 -> PasswordErrorType.LENGTH
                password.contains("\n") -> PasswordErrorType.NEWLINE
                password.contains(" ") -> PasswordErrorType.SPACE
                !networkManager.isNetworkAvailable() -> PasswordErrorType.NETWORK
                else -> PasswordErrorType.NONE
            }
    }
