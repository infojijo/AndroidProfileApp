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
import pojo.PastExperienceItem

import util.Constants

/* ProfileExperienceAdapter uses for populating
Experience Details adapter list data binding section of the app

/**
 * @param mContext - context form view to be inflated
 * @param mListExperience - data for list to display.
 */
*/

class ProfileExperienceAdapter(
    private var mContext: Context,
    private var mListExperience: List<PastExperienceItem>
) : RecyclerView.Adapter<ProfileExperienceAdapter.ExperienceViewHolder>() {

    /**
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_details_item_view, parent, false)
        return ExperienceViewHolder(view)
    }

    /**
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {

        holder.companyTitle.text = mListExperience[position].comp
        holder.companyDate.text = mListExperience[position].dates
        holder.companyRoles.text = mListExperience[position].role
        holder.companyResponsibility.text = mListExperience[position].mainResp

        Glide.with(mContext).load(Constants.URL_ICON + mListExperience[position].logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.DATA) //3
            .into(holder.companyLogo)
    }

    override fun getItemCount(): Int {
        return this.mListExperience .size
    }

    class ExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var companyTitle: TextView = itemView.findViewById(R.id.companyTitle)
        var companyDate: TextView = itemView.findViewById(R.id.companyDate)
        var companyRoles: TextView = itemView.findViewById(R.id.companyRoles)
        var companyResponsibility: TextView = itemView.findViewById(R.id.companyResp)
        var companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
    }
}
