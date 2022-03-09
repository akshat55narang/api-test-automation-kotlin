package airline

enum class AirlineDetails(
    val id: Long,
    val airlineName: String,
    val country: String,
    val logo: String,
    val slogan: String,
    val headQuaters: String,
    val website: String,
    val established: String
) {
    THAI_AIRWAYS(
        8,
        "Thai Airways",
        "Thailand",
        "https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Thai_Airways_Logo.svg/200px-Thai_Airways_Logo.svg.png",
        "Smooth as Silk / I Fly THAI",
        "Jom Phol Subdistrict, Chatuchak, Bangkok, Thailand",
        "www.thaiairways.com",
        "1960"
    ),
    CATHAY_PACIFIC(
        3,
        "Cathay Pacific",
        "Hong Kong",
        "https://upload.wikimedia.org/wikipedia/en/thumb/1/17/Cathay_Pacific_logo.svg/300px-Cathay_Pacific_logo.svg.png",
        "Move Beyond",
        "Cathay City, Hong Kong International Airport, Chek Lap Kok, Hong Kong",
        "www.cathaypacific.com",
        "1946"
    )
}
