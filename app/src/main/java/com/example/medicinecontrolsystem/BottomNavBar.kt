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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.ComposeNavigator
import com.example.medicinecontrolsystem.ui.theme.MedicineControlSystemTheme


// 导航项数据类
data class NavItem(
    val label: String,
    val icon: Int,  // 图标资源ID
    val route: String  // 路由名称（对应导航图中的id）
)

@Composable
fun BottomNavBar(navController: NavController, modifier : Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        NavItem("首页", R.drawable.ic_home, "home"),
        NavItem("记录", R.drawable.ic_record, "record"),
        NavItem("我的", R.drawable.ic_profile, "profile")
    )
    Box(modifier = modifier) {
        NavigationBar(
            modifier = Modifier
                .height(200.dp),
            containerColor = Color.White
        ){
            navItems.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            modifier= Modifier.size(90.dp),
                            imageVector = ImageVector.vectorResource(id = item.icon),
                            contentDescription = item.label
                        )
                    },
                    label = {
                        Text(
                            item.label,
                            fontSize = 45.sp,
                            fontWeight = FontWeight.W600
                        ) },
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
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBar() {
    // 添加主题包装
    MedicineControlSystemTheme {
        // 添加Surface容器
        Surface {
            val mockNavController = rememberNavController()
            BottomNavBar(navController = mockNavController)
        }
    }
}