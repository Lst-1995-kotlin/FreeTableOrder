package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.SettingRepository
import javax.inject.Inject

enum class PasswordErrorType {
    CHANGE_BLANK,
    CHANGE_CHECK_BLANK,
    NOT_MATCH,
    LENGTH_CHECK,
    NEWLINE_CHECK,
    SPACE_CHECK,
    NONE,
}

class SettingUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
    ) {
        suspend fun setTablePassword(password: String) {
            settingRepository.setTablePassword(password)
        }

        fun passwordCheck(
            password: String?,
            passwordCheck: String?,
        ): PasswordErrorType =
            when {
                password.isNullOrEmpty() -> PasswordErrorType.CHANGE_BLANK
                passwordCheck.isNullOrEmpty() -> PasswordErrorType.CHANGE_CHECK_BLANK
                password != passwordCheck -> PasswordErrorType.NOT_MATCH
                password.length < 4 -> PasswordErrorType.LENGTH_CHECK
                password.contains("\n") -> PasswordErrorType.NEWLINE_CHECK
                password.contains(" ") -> PasswordErrorType.SPACE_CHECK
                else -> PasswordErrorType.NONE
            }
    }
