import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "\"https://api.swu.de/mobility/v1\""

    val swu_api: Swurepository by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converter for JSON
            .build()
            .create(Swurepository::class.java)
    }
}