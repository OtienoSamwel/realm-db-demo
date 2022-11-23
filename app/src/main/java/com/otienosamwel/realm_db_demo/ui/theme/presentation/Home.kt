package com.otienosamwel.realm_db_demo.ui.theme.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.otienosamwel.realm_db_demo.data.models.PostsItem

@Composable
fun Home() {
    val homeViewModel: HomeViewModel = viewModel()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {

        if (homeViewModel.isLoading.value) LoadingScreen()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val postItems = homeViewModel.posts.observeAsState()
            LaunchedEffect(true) { homeViewModel.getPosts() }

            //display posts
            LazyColumn {
                postItems.value?.let { postItems ->
                    items(postItems) { postItem ->
                        PostItem(postsItem = postItem)
                    }
                }
            }

        }

        if (homeViewModel.hasNetworkError.value) {
            Snackbar() {
                Text(text = "A network error occurred")
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun PostItem(postsItem: PostsItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = postsItem.title,
            style = MaterialTheme.typography.h6
        )
        Text(text = postsItem.body)

        Spacer(modifier = Modifier.height(12.dp))
        Divider()
    }
}