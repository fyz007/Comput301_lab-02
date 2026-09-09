package com.example.listycity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity.ui.theme.LIstyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cityRepository = CityRepository()

        setContent {
            LIstyCityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityListScreen(
                        cities = cityRepository.cities,
                        onAddCity = {cityRepository.addCity(it)},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LIstyCityTheme {
        Greeting("Android")
    }
}
class CityRepository {
    private val _cities = mutableStateListOf(
        "Edmonton", "Vancouver", "GuangZhou", "ShenYang",
        "ShenZhen", "Toronto", "Beijing", "Richmond", "London"
    )

    val cities: List<String> get() = _cities

    fun addCity(city: String) {
        _cities.add(city)
    }
}

@Composable
fun CityListScreen(
    cities: List<String>,
    onAddCity:(String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newCityName by remember { mutableStateOf(value = "") }

    Column(modifier = modifier.fillMaxSize()){
        Row(modifier = modifier.padding(16.dp)){
            OutlinedTextField(
                value = newCityName,
                onValueChange = {newCityName = it},
                label = {Text("City name")},
                modifier = modifier.weight(1f)
            )
            Spacer(modifier = modifier.width(10.dp))

            Button(
                onClick = {
                    if (newCityName.isNotBlank()){
                        onAddCity(newCityName)
                        newCityName = ""
                    }
                }
            ) {
                Text("Add City")
            }
        }

        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(cities){city ->
                CityRow(city = city)

            }
        }
    }
}

@Composable
fun CityRow(city: String){
    Text(
        text = city,
        fontSize = 28.sp,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 18.dp)
    )
}

