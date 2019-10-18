package pojo

import com.google.gson.annotations.SerializedName

data class Technology(

	@field:SerializedName("tech1")
	val tech1: String? = null,

	@field:SerializedName("tech2")
	val tech2: String? = null,

	@field:SerializedName("tech3")
	val tech3: String? = null
)