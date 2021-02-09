package cz.rimu.nejlevnejsi.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteOffer(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
