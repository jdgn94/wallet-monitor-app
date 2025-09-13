package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.walletmonitor.db.v0.CategoryQueries
import app.walletmonitor.db.v0.Subcategories
import app.walletmonitor.db.v0.SubcategoryQueries

class SubcategoryViewModel(
    private val subcategoryQueries: SubcategoryQueries,
    private val categoryQueries: CategoryQueries
): ViewModel() {
    fun getAllPaginate(categoryId: Long, name: String, page: Int, pageSize: Int = 20): List<Subcategories> {
        val result = subcategoryQueries.getAllPaginate(
            value_ = "%$name%",
            categoryId = categoryId,
            value__ = pageSize.toLong(),
            value___ = (page * pageSize).toLong()
        ).executeAsList()

        return result
    }

    fun create(
        categoryId: Long,
        name: String,
        icon: String,
        color: String,
    ) {
        subcategoryQueries.insert(
            categoryId = categoryId,
            name = name,
            icon = icon,
            color = color
        )
    }

    fun update(
        id: Long,
        categoryId: Long,
        name: String,
        icon: String,
        color: String,
    ) {
        subcategoryQueries.update(
            id = id,
            categoryId = categoryId,
            name = name,
            icon = icon,
            color = color
        )
    }
}
