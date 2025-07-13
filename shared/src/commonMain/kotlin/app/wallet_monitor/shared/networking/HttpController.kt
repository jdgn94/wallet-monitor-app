package app.wallet_monitor.shared.networking

import app.wallet_monitor.shared.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.ParametersBuilder
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import app.wallet_monitor.shared.utils.NetworkError
import app.wallet_monitor.shared.utils.Result

class HttpController{
    private val httpClient: HttpClient = createHttpClient(Constants.httpClient)

    suspend fun getRequest(
        url: String,
        params: ParametersBuilder.() -> Unit = {}
    ): Result<String, NetworkError> {
        val urlString = Constants.apiUrl + url
        println("url: $urlString")
        val response = try {
            httpClient.get(
                urlString = urlString
            ) {
                parameters(params)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN)
        }

        println(response.status.value.toString())
        return when(response.status.value) {
            in 200..299 -> Result.Success(response.bodyAsText())
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            403 -> Result.Error(NetworkError.FORBIDDEN)
            404 -> Result.Error(NetworkError.NOT_FOUND)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            409 -> Result.Error(NetworkError.CONFLICT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun postRequest(
        url: String,
        params: ParametersBuilder.() -> Unit = {},
        body: Any? = null,
    ): Result<String, NetworkError> {
        val urlString = Constants.apiUrl + url
        println("url: $urlString")
        val response = try {
            httpClient.post(
                urlString = urlString
            ) {
                parameters(params)
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN)
        }

        return when(response.status.value) {
            in 200..299 -> Result.Success(response.bodyAsText())
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            403 -> Result.Error(NetworkError.FORBIDDEN)
            404 -> Result.Error(NetworkError.NOT_FOUND)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            409 -> Result.Error(NetworkError.CONFLICT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}