package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.walletmonitor.db.v0.Categories
import app.walletmonitor.db.v0.CategoryQueries
import app.walletmonitor.db.v0.Subcategories
import app.walletmonitor.db.v0.SubcategoryQueries

class CategoryViewModel(
    private val categoryQueries: CategoryQueries,
    private val subcategoryQueries: SubcategoryQueries
): ViewModel() {
    fun getAll(): List<Categories> {
        val result = categoryQueries.getAll().executeAsList()

        return result
    }

    fun getOne(id: Long): Categories? {
        return categoryQueries.getOne(id).executeAsOneOrNull()
    }

    fun getAllPaginate(name: String, page: Int, pageSize: Int = 20): List<Categories> {
        val result = categoryQueries.getAllPaginate(
            value_ ="%$name%",
            value__ = pageSize.toLong(),
            value___ = (page * pageSize).toLong()
        ).executeAsList()

        return result
    }

    fun createOrUpdate(
        id: Long,
        name: String,
        description: String,
        icon: String,
        color: String,
        subcategories: List<Subcategories>
    ) {
        var idCreated = id
        if (id == 0L)
             idCreated = create(name, description, icon, color)
        else
            update(id, name, description, icon, color)

        val viewModel = SubcategoryViewModel(subcategoryQueries, categoryQueries)
        subcategories.map{ subcategories ->
            viewModel.createOrUpdate(
                subcategories.id,
                idCreated,
                subcategories.name,
                subcategories.icon,
                subcategories.color
            )
        }
    }

    private fun create(
        name: String,
        description: String,
        icon: String,
        color: String,
    ): Long {
        val res = categoryQueries.insert(
            name = name,
            description = description,
            icon = icon,
            color = color
        )
        return res.value
    }

    private fun update(
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
