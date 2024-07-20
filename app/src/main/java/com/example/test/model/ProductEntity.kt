
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product_table")
data class ProductEntity(
    @PrimaryKey
    val id: Int
)