package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.wallet_monitor.shared.networking.HttpController
import app.wallet_monitor.shared.utils.onError
import app.wallet_monitor.shared.utils.onSuccess

class TestingViewModel: ViewModel() {
    private val httpController: HttpController = HttpController()
    suspend fun getPokemon() {
        httpController.getRequest(
            url = "pokemon/ditto",
        )
            .onSuccess {
                println(it.toString())
            }
            .onError {
                println(it.toString())
            }
    }
}