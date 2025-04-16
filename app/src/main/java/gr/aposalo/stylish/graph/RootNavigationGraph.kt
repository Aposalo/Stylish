package gr.aposalo.stylish.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gr.aposalo.stylish.ui.composables.details.DetailsScreen
import gr.aposalo.stylish.ui.composables.login.LoginScreen
import gr.aposalo.stylish.ui.composables.main.MainScreen
import gr.aposalo.stylish.ui.composables.splash.SplashScreen


@Composable
fun RootNavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Graph.SPLASH,
        route = Graph.ROOT
    ) {

        composable(route = Graph.SPLASH) {
            SplashScreen(
                onAnimationFinished = {
                    navController.navigate(it)
                }
            )
        }

        composable(route = Graph.LOGIN) {
            LoginScreen(
                onLoginClicked = {
                    navController.navigate(Graph.HOME)
                }
            )
        }

        composable(route = Graph.HOME) {
            MainScreen(
                onProductDetailsClicked = { id ->
                    navController.navigate(Graph.DETAILS  + "/$id")
                }
            )
        }
        composable(route = Graph.DETAILS + "/{id}") {

            val id = it.arguments?.getString("id")!!

            DetailsScreen(
                id = id,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Graph.DETAILS_EDIT) {
//            DetailsEditScreen()
        }
    }
}

//this contains the routes of every navigation graph we have in our application
object Graph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash"
    const val LOGIN = "main"
    const val HOME = "home"
    const val DETAILS = "details"
    const val DETAILS_EDIT = "details_edit"
}