package com.example.bankcardapp.presentation.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.bankcardapp.domain.model.BinInfo

@Composable
fun BinInfoDisplay(binInfo: BinInfo) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        binInfo.number?.let { numberInfo ->
            item {
                InfoRow(label = "Number Length", value = numberInfo.length?.toString())
                InfoRow(label = "Luhn Check", value = numberInfo.luhn?.toString())
            }
        }
        item { InfoRow(label = "Scheme", value = binInfo.scheme) }
        item { InfoRow(label = "Type", value = binInfo.type) }
        item { InfoRow(label = "Brand", value = binInfo.brand) }
        item { InfoRow(label = "Prepaid", value = binInfo.prepaid?.toString()) }

        binInfo.country?.let { countryInfo ->
            item {
                InfoRow(label = "Country Name", value = countryInfo.name)
                InfoRow(label = "Country Code", value = countryInfo.alpha2)
                InfoRow(label = "Emoji", value = countryInfo.emoji)
                InfoRow(label = "Currency", value = countryInfo.currency)
                InfoRow(label = "Coordinates") {
                    val latitude = countryInfo.latitude
                    val longitude = countryInfo.longitude
                    if (latitude != null && longitude != null) {
                        Text(
                            text = "$latitude, $longitude",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                val geoUri = "geo:$latitude,$longitude"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }

        binInfo.bank?.let { bankInfo ->
            item {
                InfoRow(label = "Bank Name", value = bankInfo.name)
                InfoRow(label = "Bank URL") {
                    bankInfo.url?.let { url ->
                        Text(
                            text = url,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
                InfoRow(label = "Bank Phone") {
                    bankInfo.phone?.let { phone ->
                        Text(
                            text = phone,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
                InfoRow(label = "Bank City", value = bankInfo.city)
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String? = null, content: @Composable (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        } else if (content != null) {
            Box(modifier = Modifier.weight(1f)) {
                content()
            }
        }
    }
}
