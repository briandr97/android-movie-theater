package woowacourse.app.presentation.ui.main.home.adapter.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.app.presentation.model.HomeData
import woowacourse.app.presentation.model.movie.MovieUiModel
import woowacourse.app.presentation.ui.main.home.adapter.HomeViewType
import woowacourse.movie.R

class HomeAdapter(
    context: Context,
    private val clickBook: (MovieUiModel) -> Unit,
    private val clickAd: (Intent) -> Unit,
) : RecyclerView.Adapter<HomeViewHolder>() {
    private val movies = mutableListOf<HomeData>()
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val homeViewType = HomeViewType.getMainViewType(viewType)
        val viewHolder: HomeViewHolder = when (homeViewType) {
            HomeViewType.CONTENT -> {
                MovieViewHolder(layoutInflater.inflate(R.layout.movie_list_item, parent, false))
            }
            HomeViewType.ADVERTISEMENT -> {
                AdvertisementViewHolder(
                    layoutInflater.inflate(R.layout.advertisement_list_item, parent, false),
                )
            }
        }
        setViewHolderClick(viewHolder)
        return viewHolder
    }

    private fun setViewHolderClick(viewHolder: HomeViewHolder) {
        when (viewHolder) {
            is MovieViewHolder -> viewHolder.setBookingClick(clickBook)
            is AdvertisementViewHolder -> viewHolder.setAdvertisementClick(clickAd)
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
    override fun getItemViewType(position: Int): Int = movies[position].homeViewType.ordinal

    fun initMovies(items: List<woowacourse.app.presentation.model.HomeData>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }
}