package app.jdgn.wallet_monitor.ui.src

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.jdgn.wallet_monitor.ui.screens.AccountScreen
import app.jdgn.wallet_monitor.ui.screens.BankScreen
import app.jdgn.wallet_monitor.ui.screens.CategoriesScreen
import app.jdgn.wallet_monitor.ui.screens.CategoryScreen
import app.jdgn.wallet_monitor.ui.screens.HomeScreen
import app.jdgn.wallet_monitor.ui.screens.InitialConfigScreen
import app.jdgn.wallet_monitor.ui.screens.SplashScreen
import app.jdgn.wallet_monitor.ui.screens.WelcomeScreen
import app.jdgn.wallet_monitor.utils.AppConstants

expect fun NavBackStackEntry.getArgumentString(key: String): String?

object Routes {
    const val SPLASH = "splash"
    const val WELCOME = "welcome"
    const val INITIAL_CONFIG = "initial_config"
    const val HOME = "home"
    const val ACCOUNT = "account/{id}"
    const val BANK = "bank/{id}"
    const val CATEGORIES = "categories"
    const val CATEGORY = "category/{id}"
}

@Composable
fun Navigation(modifier:Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(route = Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(route = Routes.WELCOME) {
            WelcomeScreen(navController)
        }

        composable(route = Routes.INITIAL_CONFIG) {
            InitialConfigScreen(navController)
        }

        composable(route = Routes.HOME) {
            HomeScreen(navController)
        }

        composable(
            route = Routes.ACCOUNT,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.getArgumentString("id")?.toLongOrNull()
            AccountScreen(navController, id)
        }

        composable(
            route = Routes.BANK,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.getArgumentString("id")?.toLongOrNull()
            BankScreen(navController, id)
        }

        composable(route = Routes.CATEGORIES) {
            CategoriesScreen(navController)
        }

        composable(
            route = Routes.CATEGORY,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.getArgumentString("id")?.toLongOrNull()
            CategoryScreen(navController, id)
        }
    }
}

fun navigateTo(
    routeName: String,
    navController: NavController
) {
    when (routeName) {
        AppConstants.BACK_ROUTE.toString() -> {
            navController.popBackStack()
        }
        else -> {
            navController.navigate(routeName)
        }
    }
}
