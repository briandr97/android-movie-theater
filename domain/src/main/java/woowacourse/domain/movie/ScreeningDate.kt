package woowacourse.domain.movie

import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.LocalDate
import java.time.LocalTime

data class ScreeningDate(val value: LocalDate) : Comparable<ScreeningDate> {

    val screeningTimes: List<LocalTime> = when (value.dayOfWeek) {
        in MONDAY..FRIDAY -> getScreeningTimes(
            WEEKDAY_SCREENING_START_TIME,
        )
        in SATURDAY..SUNDAY -> getScreeningTimes(
            WEEKEND_SCREENING_START_TIME,
        )
        else -> listOf()
    }

    private fun getScreeningTimes(
        startHour: Int,
        endHour: Int = SCREENING_END_HOUR,
    ): List<LocalTime> {
        val screeningTimes = mutableListOf<LocalTime>()
        var currentHour = startHour

        while (currentHour < endHour) {
            screeningTimes.add(LocalTime.of(currentHour, 0))
            currentHour += SCREENING_TERM
        }
        return screeningTimes.toList()
    }

    override fun compareTo(other: ScreeningDate) = when {
        this.value > other.value -> 1
        this.value < other.value -> -1
        else -> 0
    }

    companion object {
        private const val SCREENING_TERM = 2
        private const val SCREENING_END_HOUR = 24
        private const val WEEKEND_SCREENING_START_TIME = 9
        private const val WEEKDAY_SCREENING_START_TIME = 10
    }
}
