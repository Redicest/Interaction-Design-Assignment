package com.example.medicinecontrolsystem


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text



// 导航项数据类
data class NavItem(
    val label: String,
    val icon: Int,  // 图标资源ID
    val route: String  // 路由名称（对应导航图中的id）
)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        NavItem("首页", R.drawable.ic_home, "home"),
        NavItem("记录", R.drawable.ic_record, "record"),
        NavItem("我的", R.drawable.ic_profile, "profile")
    )

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // 导航配置
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBar() {
    // 注意：预览中无法获取真实的 NavController
    // 这里仅用于 UI 预览
    val mockNavController = rememberNavController()
    BottomNavBar(navController = mockNavController)
}