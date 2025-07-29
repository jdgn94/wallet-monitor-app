package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.walletmonitor.db.v0.AccountQueries
import app.walletmonitor.db.v0.GetAll
import app.walletmonitor.db.v0.GetOne
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class AccountViewModel(
    private val accountQueries: AccountQueries
): ViewModel() {
    private var _accounts: List<GetAll> = listOf()

    fun getAccounts(): List<GetAll> {
        if (!_accounts.isEmpty())
            return _accounts

        _accounts = accountQueries.getAll().executeAsList()
            .also { _accounts = it }
        return _accounts
    }

    fun getAccount(id: Long): GetOne? {
        return accountQueries.getOne(id).executeAsOneOrNull()
    }

    @OptIn(ExperimentalTime::class)
    fun createAccount(
        currencyId: Long,
        bankId: Long? = null,
        amount: Double,
        color: String,
        name: String
    ) {
        accountQueries.insert(
            currencyId = currencyId,
            bankId = bankId,
            amount = amount,
            color = color,
            name = name,
            updatedAt = Clock.System.now().toString(),
        )
    }
}