package woowacourse.movie.ui.completed

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.ReservationUiModel
import woowacourse.movie.movie.MovieRepository
import woowacourse.movie.ui.seat.NotiChannel
import woowacourse.movie.util.createChannel
import woowacourse.movie.util.formatScreenDateTime
import woowacourse.movie.util.getParcelable
import woowacourse.movie.util.requestNotificationPermission
import woowacourse.movie.util.shortToast

class CompletedActivity : AppCompatActivity() {
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                shortToast(R.string.permission_denied)
            } else {
                shortToast(R.string.permission_granted)
                createChannel(this, NotiChannel.BOOKING_ALARM, NotificationManager.IMPORTANCE_HIGH)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)

        requestNotificationPermission(this) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        getResult()?.let { initView(it) } ?: finish()
    }

    private fun getResult(): ReservationUiModel? {
        return intent.getParcelable(RESERVATION, ReservationUiModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(reservation: ReservationUiModel) {
        val movie = MovieRepository.getMovie(reservation.movieId)
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            reservation.bookedDateTime.formatScreenDateTime()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.ticket_count_seat_info, reservation.count, reservation.seatPosition)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount, reservation.payment)
        showBackButton()
    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val RESERVATION = "RESERVATION"

        fun getIntent(context: Context, reservation: ReservationUiModel): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(RESERVATION, reservation)
            }
        }
    }
}
