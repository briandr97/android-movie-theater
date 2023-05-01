package woowacourse.app.ui.main.adapter.listview

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.app.model.main.MainData
import woowacourse.app.model.main.MovieUiModel
import woowacourse.app.ui.main.adapter.MainViewType
import woowacourse.app.util.formatScreenDate
import woowacourse.movie.R

class MovieViewHolder(view: View) : MainViewHolder(view) {
    private val thumbnail: ImageView = view.findViewById(R.id.imageItemThumbnail)
    private val title: TextView = view.findViewById(R.id.textItemTitle)
    private val date: TextView = view.findViewById(R.id.textBookingScreeningDate)
    private val runningTime: TextView = view.findViewById(R.id.textBookingRunningTime)
    private val button: Button = view.findViewById(R.id.buttonItemBook)

    override val mainViewType: MainViewType = MainViewType.CONTENT
    private lateinit var movie: MovieUiModel

    override fun onBind(data: MainData) {
        movie = data as MovieUiModel
        thumbnail.setImageResource(movie.thumbnail)
        title.text = movie.title
        date.text = view.context.getString(
            R.string.screening_date,
            movie.startDate.formatScreenDate(),
            movie.endDate.formatScreenDate(),
        )
        runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
    }

    fun clickBookButton(clickBook: (Long) -> Unit) {
        button.setOnClickListener {
            clickBook(movie.id)
        }
    }
}