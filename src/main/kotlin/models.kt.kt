@Entity
data class User(
    @Id
    var id: Long = 0,
    var name: String? = null
)