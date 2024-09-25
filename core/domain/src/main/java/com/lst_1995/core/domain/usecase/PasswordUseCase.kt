package com.lst_1995.core.domain.usecase

import com.lst_1995.core.domain.repository.SettingRepository
import javax.inject.Inject

enum class PasswordEvent {
    CHANGE_BLANK,
    CHANGE_CHECK_BLANK,
    NOT_MATCH,
    LENGTH,
    NEWLINE,
    SPACE,
    NONE,
    CHANGE_SUCCESS,
}

class PasswordUseCase
    @Inject
    constructor(
        private val settingRepository: SettingRepository,
    ) {
        suspend fun setTablePassword(password: String) = settingRepository.setTablePassword(password)

        fun passwordCheck(
            password: String?,
            passwordCheck: String?,
        ): PasswordEvent =
            when {
                password.isNullOrEmpty() -> PasswordEvent.CHANGE_BLANK
                passwordCheck.isNullOrEmpty() -> PasswordEvent.CHANGE_CHECK_BLANK
                password != passwordCheck -> PasswordEvent.NOT_MATCH
                password.length < 4 -> PasswordEvent.LENGTH
                password.contains("\n") -> PasswordEvent.NEWLINE
                password.contains(" ") -> PasswordEvent.SPACE
                else -> PasswordEvent.NONE
            }
    }
