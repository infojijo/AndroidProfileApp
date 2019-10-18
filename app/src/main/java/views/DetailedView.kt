package views

import adapters.ProfileEducationAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.profile.R
import adapters.ProfileExperienceAdapter
import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.profile_deails_view.*
import pojo.EducationItem
import pojo.PastExperienceItem
import util.Constants

/**
 * DetailedView for the details of the profile.
 * Recylerview has been reused for both the adapters.
 */
class DetailedView : AppCompatActivity() {

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_deails_view)

        itemListRecyclerView.layoutManager = LinearLayoutManager(this)
        itemListRecyclerView.itemAnimator = DefaultItemAnimator()

        if (intent.extras.get(Constants.KEY_FROM_PROFESSIONAL) == true) {

            pastExperienceItem?.let {
                val expListAdapter = ProfileExperienceAdapter(applicationContext, it)
                itemListRecyclerView.adapter = expListAdapter
            }
        } else {
            educationItem?.let {
                val expListAdapter = ProfileEducationAdapter(applicationContext, it)
                itemListRecyclerView.adapter = expListAdapter
            }
        }

    }

    companion object {
        var pastExperienceItem: List<PastExperienceItem>? = null
        var educationItem: List<EducationItem>? = null

        fun startDetailViewActivity(
            context: Context, experience: List<PastExperienceItem>?,
            education: List<EducationItem>, isExp: Boolean
        ) {
            pastExperienceItem = experience
            educationItem = education

            context.startActivity(
                Intent(context, DetailedView::class.java).putExtra(
                    Constants.KEY_FROM_PROFESSIONAL,
                    isExp
                )
            )
        }
    }
}
