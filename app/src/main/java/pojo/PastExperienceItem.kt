package pojo

import com.google.gson.annotations.SerializedName

data class PastExperienceItem(

	@field:SerializedName("comp")
	val comp: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("dates")
	val dates: String? = null,

	@field:SerializedName("main_resp")
	val mainResp: String? = null
)