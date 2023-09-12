package pl.strefakursow.noiquattro

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pl.strefakursow.noiquattro.ui.screens.*
import pl.strefakursow.noiquattro.ui.theme.NoiquattroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoiquattroTheme {
                val navController = rememberNavController()
                val vm = viewModel<MainViewModel>()

                NavigationComponent(navController = navController, vm = vm)
            }
        }
    }

}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    vm: MainViewModel,
) {
    val loginScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = "start") {

        composable(route = "start") {
            StartScreen(onStartClick = { navController.navigate(route = "login") })
        }
        composable(route = "login") {
            LoginScreen(
                onClickLogin = { email, password ->
                    loginScope.launch {
                        vm.login(email, password)
                        navController.navigate(route = "home")
                    }
                },
                onClickGoogle = {})
        }
        composable(route = "home") {

            val home = vm.home

            if (home != null)
                HomeScreen(data = home,
                    onItemClick = { item ->
                        vm.updateItemState(id = item.id)
                        navController.navigate("item_detail")
                    },
                    onProfileClick = {
                        vm.updateProfileState()
                        navController.navigate("profile")
                    },
                    onSearch = { str -> vm.filterData(str) })
        }
        composable(route = "item_detail") {
            val itemDetail = vm.item

            if (itemDetail != null)
                ProductDetailScreen(
                    data = itemDetail,
                    onItemAdd = { item -> vm.addItem(item) },
                    onGoToShoppingBag = { navController.navigate("shopping_bag") })

        }
        composable(route = "profile") {
            val profileData = vm.profileState

            if (profileData != null)
                ProfileScreen(
                    data = profileData,
                    onHistoryClick = { navController.navigate("order_history") })

        }
        composable(route = "shopping_bag") {

            val orderList = vm.shoppingBag.toList()
            val amount = vm.amount

            ShoppingBagScreen(
                shoppingList = orderList,
                roundedDouble = amount,
                onIncrementOrderNumber = { vm.increaseOrderNumber(it) },
                onDecrementOrderNumber = { vm.decreaseOrderNumber(it) },
                onPaymentClick = {
                    vm.preparePayment()
                    navController.navigate("payment")
                }
            )

        }
        composable(route = "payment") {

            val payment = vm.payment

            if (payment != null) {
                PaymentScreen(
                    data = payment,
                    onClose = {
                        navController.navigate(
                            route = "shopping_bag",
                            navOptions = navOptions {
                                popUpTo("shopping_bag") {
                                    inclusive = true
                                }
                            })
                    },
                    onPayClick = {
                        vm.updateMapState()
                        vm.clearShoppingBag()

                        navController.navigate(
                            route = "map",
                            navOptions = navOptions { popUpTo("home") })
                    })
            }

        }
        composable(route = "order_history") {
            val orderHistory = vm.orderHistory

            OrderHistoryScreen(
                data = orderHistory,
                onEmptyHistoryClick = {
                    navController.navigate(route = "home", navOptions = navOptions {
                        popUpTo("order_history") {
                            inclusive = true
                        }
                    })
                })

        }
        composable(route = "map") {

            val mapData = vm.mapDetail
            if (mapData != null) {
                MapScreen(data = mapData)
            }

        }
    }
}
