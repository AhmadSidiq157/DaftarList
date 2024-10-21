package id.sidiqimawan.daftarlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.sidiqimawan.daftarlist.affirmations.Affirmation
import id.sidiqimawan.daftarlist.affirmations.Datasource
import id.sidiqimawan.daftarlist.ui.theme.DaftarListTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaftarListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    AffirmationList(
        affirmationList = Datasource().loadAffirmations(),
    )
}
//untuk menampilkan daftar animasi dalam bentuk list vertikal menggunakan LAzyColumn
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp) // padding di setiap affirmation
            )
        }
    }
}
//untuk menampilkan satu kartu affirmation yang terdiri dari gambar dan text
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId), // mengambil resource gambar berdasarkan ID dari objek affirmation
                contentDescription = stringResource(affirmation.stringResourceId), //menggunakan string resource dari affirmation.stringResourceId untuk menambah aksesibilitas
                modifier = Modifier
                    .fillMaxWidth() // memastikan ganbar mengambil lebar pebuh dari kartu
                    .height(194.dp), // untuk menetapkan tinggi gambar
                contentScale = ContentScale.Crop // membuat gambar memenuhi seluruh area yang diberikan, memotong bagian yang tidak sesuai
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId), // digunakan untuk mengambil teks dari resources sesuai dengan ID teks yang diberikan
                modifier = Modifier.padding(16.dp), // memberikan padding di sekitar teks
                style = MaterialTheme.typography.headlineSmall // mengatur gaya teks sesuai dengan tipografi dari tema aplikasi.
            )
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() { // untuk menampilkan satu kartu afirmasi di dalam Android Studio tanpa menjalankan aplikasi
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}
