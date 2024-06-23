package com.dhruv.retrofit1.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController,
) {
    val pincodeData by viewModel.pincodeData.observeAsState()
    var searching by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "PincodeSearch")
            })
        }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    value = searching,
                    onValueChange = { searching = it },
                    placeholder = { Text("Search by Pincode or District Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                if (searching.isEmpty()){
                    Text(text = "Search by Pincode or District Name")
                }else{

                    val filteredData = pincodeData!!.filter { pincode ->
                        pincode.pincode.contains(searching, ignoreCase = true) ||
                                pincode.districtname.contains(searching, ignoreCase = true)||
                                pincode.officename.contains(searching , ignoreCase = true)||
                                pincode.officetype.contains(searching , ignoreCase = true)
                    }
                    if (filteredData.isNotEmpty()) {
                        LazyColumn {
                            items(filteredData) { pincode ->
                                ItemData(
                                    pincode = pincode.pincode,
                                    district = pincode.districtname
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "No matching pincode found.",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}


//
//when {
//    searching.isEmpty() -> {
//        Text(
//            text = "Enter Pincode or District Name",
//            modifier = modifier.align(Alignment.CenterHorizontally)
//        )
//    }
//    searching.isNotEmpty() -> {
//        val filteredData = pincodeData!!.filter { pincode ->
//            pincode.pincode.contains(searching, ignoreCase = true) ||
//                    pincode.districtname.contains(searching, ignoreCase = true)||
//                    pincode.officename.contains(searching , ignoreCase = true)||
//                    pincode.officetype.contains(searching , ignoreCase = true)
//        }
//        if (filteredData.isNotEmpty()) {
//            LazyColumn {
//                items(filteredData) { pincode ->
//                    ItemData(
//                        pincode = pincode.pincode,
//                        district = pincode.districtname
//                    )
//                }
//            }
//        } else {
//            Text(
//                text = "No matching pincode found.",
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//        }
//    }
//}


@Composable
fun ItemData(modifier: Modifier = Modifier, pincode: String, district: String) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Pincode: $pincode, District: $district")
        }
    }

}
