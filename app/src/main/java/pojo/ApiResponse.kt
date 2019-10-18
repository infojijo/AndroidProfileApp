package pojo

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("Professional")
	val professional: Professional? = null,

	@field:SerializedName("Technology")
	val technology: Technology? = null,

	@field:SerializedName("Education")
	val education: List<EducationItem?>? = null,

	@field:SerializedName("PastExperience")
	val pastExperience: List<PastExperienceItem?>? = null
)