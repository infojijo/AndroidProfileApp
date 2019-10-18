package pojo

import com.google.gson.annotations.SerializedName

data class Professional(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("yearsofexp")
	val yearsofexp: String? = null,

	@field:SerializedName("designation")
	val designation: String? = null
)