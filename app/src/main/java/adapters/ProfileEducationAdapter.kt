package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.app.profile.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pojo.EducationItem

import util.Constants

/* ProfileEducationAdapter uses for populating
Educational Qualification adapter list data binding section of the app
/**
 * @param mContext - context from the view
 * @param mListEducation - data for the recyclerview.
 */
*/
class ProfileEducationAdapter(private var mContext: Context, private var mListEducation: List<EducationItem>) :
    RecyclerView.Adapter<ProfileEducationAdapter.EducationViewHolder>() {

    /**
     * @param parent
     * @param viewType
     * @return
     */
    //returns view after inflating with the layout.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_details_item_view, parent, false)

        return EducationViewHolder(view)
    }

    /**
     * @param holder
     * @param position
     */
    //binding the viewholder to the view.
    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {

        holder.eduTitle.text = mListEducation[position].education
        holder.eduUniversity.text = mListEducation[position].university

        Glide.with(mContext).load(
            Constants.URL_ICON + mListEducation[position].logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.DATA) //3
            .into(holder.eduLogo)

    }

    override fun getItemCount(): Int {
        return this.mListEducation.size
    }


    //view holder for the education details.
    class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var eduTitle: TextView = itemView.findViewById(R.id.companyTitle)
        var eduUniversity: TextView = itemView.findViewById(R.id.companyDate)
        var eduLogo: ImageView = itemView.findViewById(R.id.companyLogo)

        init {
            //unnecessary view is being hidden from the reusable adapter view
            itemView.findViewById<View>(R.id.companyRoles).visibility = View.GONE
            itemView.findViewById<View>(R.id.companyResp).visibility = View.GONE
        }
    }
}
