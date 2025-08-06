package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.walletmonitor.db.v0.BankQueries
import app.walletmonitor.db.v0.Banks

class BankViewModel(
    private val bankQueries: BankQueries
): ViewModel() {
    private var _banks: List<Banks> = listOf()

    fun getBanks(): List<Banks> {
        if (_banks.isNotEmpty())
            return _banks

        _banks = bankQueries.getAll().executeAsList()
            .also { _banks = it }
        return _banks
    }

    fun createBank(
        name: String,
        image: String? = null
    ) {
        bankQueries.insert(
            name = name,
            image = image
        )
    }
}