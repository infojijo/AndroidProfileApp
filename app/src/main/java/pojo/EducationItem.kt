package pojo

import com.google.gson.annotations.SerializedName

data class EducationItem(

	@field:SerializedName("education")
	val education: String? = null,

	@field:SerializedName("university")
	val university: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null
)