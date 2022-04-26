package com.example.pagginatedlazycolumncompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.pagginatedlazycolumncompose.databinding.MainlayoutBinding
import com.example.pagginatedlazycolumncompose.ui.theme.PagginatedLazyColumnComposeTheme
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    private lateinit var viewBinding: MainlayoutBinding
    private val employeeViewModel: EmpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = MainlayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.composeView.setContent {
            var openDialog by remember { mutableStateOf(false) }
            Column/*(modifier = Modifier.verticalScroll(rememberScrollState())) */{
                repeat(20) {
                    Text("Android Dev", Modifier.clickable { openDialog = true })
                }
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier.height(2.dp), color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                UserInfoList(userList = employeeViewModel.user, this@MainActivity)
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier.height(2.dp), color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                repeat(20) {
                    Greeting("Yasir Tanveer")
                }
            }
            ShowAlertDialog(openDialog,
                { openDialog = false },
                { openDialog = false },
                { openDialog = false })
        }

//        setContent {
//            PagginatedLazyColumnComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Column {
//                        Greeting("Android")
//                        UserInfoList(userList = employeeViewModel.user, this@MainActivity)
//                    }
//                }
//            }
//        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserInfoList(userList: Flow<PagingData<User>>, context: Context) {
    val userListItems: LazyPagingItems<User> = userList.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.height(200.dp)) {
        items(userListItems) { item ->
            item?.let {
                EmployeeItem(empData = it, onClick = {
                    Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    //You can use modifier to show error message
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
    Divider(modifier = Modifier.height(2.dp), color = Color.Black)
    Spacer(modifier = Modifier.height(10.dp))

    LazyRow {
        items(userListItems) { item ->
            item?.let {
                EmployeeItem(empData = it, onClick = {
                    Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    //You can use modifier to show error message
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
    Divider(modifier = Modifier.height(2.dp), color = Color.Black)
    Spacer(modifier = Modifier.height(10.dp))

    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.height(200.dp)) {
        items(userListItems.itemCount) { index ->
            userListItems[index]?.let {
                EmployeeItem(empData = it, onClick = {
                    Toast.makeText(context, userListItems[index]?.id.toString(), Toast.LENGTH_SHORT)
                        .show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    //You can use modifier to show error message
                }
            }
        }
    }
}

@Composable
fun EmployeeItem(empData: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f
                )
            ) {
                val image = rememberCoilPainter(
                    request = empData.avatar,
                    fadeIn = true
                )
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = empData.first_name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black
                )
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = empData.email,
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    confirmButton: () -> Unit,
    dismissButton: () -> Unit
) {
    if (openDialog)
        AlertDialog(
            { onDismissRequest() },
            confirmButton = { Button({ confirmButton() }) { Text(text = "OK") } },
            dismissButton = {
                Button({ dismissButton() }) { Text(text = "Cancel") }
            },
            title = { Text(text = "Hello") },
            text = { Text(text = "Hello Yasir Tanveer") },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PagginatedLazyColumnComposeTheme {
        Greeting("Android")
    }
}