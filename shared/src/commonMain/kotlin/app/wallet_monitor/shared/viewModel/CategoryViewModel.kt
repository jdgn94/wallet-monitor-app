package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.walletmonitor.db.v0.Categories
import app.walletmonitor.db.v0.CategoryQueries

class CategoryViewModel(
    private val categoryQueries: CategoryQueries
): ViewModel() {
    fun getAllPaginate(name: String, page: Int, pageSize: Int = 20): List<Categories> {
        val result = categoryQueries.getAllPaginate(
            value_ ="%$name%",
            value__ = pageSize.toLong(),
            value___ = (page * pageSize).toLong()
        ).executeAsList()

        return result
    }

    fun create(
        name: String,
        description: String,
        icon: String,
        color: String,
    ) {
        categoryQueries.insert(
            name = name,
            description = description,
            icon = icon,
            color = color
        )
    }

    fun update(
        id: Long,
        name: String,
        description: String,
        icon: String,
        color: String,
    ) {
        categoryQueries.update(
            id = id,
            name = name,
            description = description,
            icon = icon,
            color = color
        )
    }
}
