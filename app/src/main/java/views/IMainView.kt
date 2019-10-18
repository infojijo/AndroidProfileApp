package views

interface IMainView {

    fun initializeView()

    fun initializeViewModel()

    fun getProfileInformation()

    fun updateUIForNetworkAvailability(availability: Boolean)

    fun showProgress()

    fun hideProgress()

    fun showNetworkError()

    fun hideNetworkError()
}
