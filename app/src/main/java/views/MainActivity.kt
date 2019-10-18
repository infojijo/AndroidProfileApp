package views

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.profile.R
import com.app.profile.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_layout.*
import network.ResponseCodes
import pojo.EducationItem
import pojo.PastExperienceItem
import pojo.ApiResponseDataSet
import util.NetworkUtils
import viewmodel.ProfileViewModel

/**
 * MainActivity with Professional Details.
 */
class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var apiResponseDataInView: ApiResponseDataSet
    private lateinit var layoutNetworkError: RelativeLayout
    private lateinit var homeNetworkError: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        initializeViewModel()
        getProfileInformation()
    }

    /**
     * initializes the views in the main acivity.
     */
    override fun initializeView() {
        layoutNetworkError = findViewById(R.id.layout_home_no_network)
        t_home_network_retry.setOnClickListener { getProfileInformation() }
        homeNetworkError = findViewById(R.id.home_network_error)
    }

    /**
     * @param apiResponseDataInView
     * checking the responsecode for the suitable action in the mainview.
     */
    private fun validateResponses(apiResponseDataInView: ApiResponseDataSet) {
        when (apiResponseDataInView.responseCodes) {
            ResponseCodes.SUCCESS -> assignValues()
            ResponseCodes.FAILURE -> showServerError()
            ResponseCodes.NETWORK_ERROR -> showNetworkError()
        }
    }

    /**
     * Binding data for MainActivity
     */
    private fun assignValues() {

        //Uses Databinding for setting values for the Profile Information section.
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        )

        val professional = apiResponseDataInView.apiResponse!!.professional
        val technology = apiResponseDataInView.apiResponse!!.technology
        activityMainBinding.profile = professional
        activityMainBinding.tech = technology

        //to prevent experienceDetails page click in case if no data is fetched.
        isDataFetch = true
    }

    /**
     * Initializes the Viewmodel.
     */
    override fun initializeViewModel() {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    /**
     * getting ProfileInformation & observer is registered to the view.
     */
    override fun getProfileInformation() {
        showProgress()

        profileViewModel.getProfileData(NetworkUtils.isNetworkConnected(this))

        profileViewModel.profileLiveData.observe(this, Observer {
            apiResponseDataInView = it
            validateResponses(it)
            hideProgress()
        })
    }

    /**
     * @param availability - on network availability - suitable message is displayed to the user.
     */
    override fun updateUIForNetworkAvailability(availability: Boolean) {

        if (availability) {
            if (layoutNetworkError.visibility == View.VISIBLE) {
                hideNetworkError()
            }
        } else {
            showNetworkError()
            hideProgress()
        }
    }

    /**
     * @param view - onclick is performed with the logic if data is already fetched for Experience details.
     */
    fun openExperienceDetails(view: View) {
        if (isDataFetch) {
            card_item_edu.isEnabled = false
            card_item_exp.isEnabled = false
            val pastExperience = apiResponseDataInView.apiResponse?.pastExperience
            val education = apiResponseDataInView.apiResponse?.education

            DetailedView.startDetailViewActivity(this,
                pastExperience as List<PastExperienceItem>?,
                education as List<EducationItem>,
                true)
            hideNetworkError()
        } else {
            showNetworkError()
        }
    }

    /**
     * @param view - onclick is performed with the logic if data is already fetched for Education details.
     */
    fun openEducationDetails(view: View) {
        if (isDataFetch) {
            card_item_edu.isEnabled = false
            card_item_exp.isEnabled = false
            val pastExperience = apiResponseDataInView.apiResponse?.pastExperience
            val education = apiResponseDataInView.apiResponse?.education

            DetailedView.startDetailViewActivity(this,
                pastExperience as List<PastExperienceItem>?,
                education as List<EducationItem>,
                false)
            hideNetworkError()
        } else {
            showNetworkError()
        }
    }

    /**
     * to show progressbar on network loading.
     */
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    /**
     * to hide progressbar on network loading.
     */
    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    /**
     * enabling the button views for handling multiple click in same view
     */
    override fun onStop() {
        super.onStop()
        card_item_edu.isEnabled = true
        card_item_exp.isEnabled = true
    }

    /**
     * enabling the isDataFetch variable to reset the data fetch for details screen.
     */
    override fun onDestroy() {
        super.onDestroy()
        isDataFetch = false
    }

    /**
     * to show Network error in MainActivity.
     */
    override fun showNetworkError() {
        layoutNetworkError.visibility = View.VISIBLE
    }

    /**
     * to hide Network error in MainActivity.
     */
    override fun hideNetworkError() {
        layoutNetworkError.visibility = View.INVISIBLE
    }

    /**
     * to show Server error in MainActivity.
     */
    private fun showServerError() {
        showNetworkError()
        homeNetworkError.setText(R.string.server_not_responding)
    }

    companion object {
        private var isDataFetch = false
    }
}
