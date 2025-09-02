package org.riezki.dummyjsonapp.presenter.product_detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dummyjsonapp.composeapp.generated.resources.Res
import dummyjsonapp.composeapp.generated.resources.description
import dummyjsonapp.composeapp.generated.resources.gallery
import dummyjsonapp.composeapp.generated.resources.information
import dummyjsonapp.composeapp.generated.resources.tags
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.riezki.dummyjsonapp.domain.model.ProductDetail
import org.riezki.dummyjsonapp.presenter.product_detail.state.ProductDetailState
import org.riezki.dummyjsonapp.presenter.product_detail.view_model.ProductDetailViewModel

/**
 * @author riezky maisyar
 */

@Composable
fun ProductDetailScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductDetailScreen(
        modifier = modifier,
        state = state
    )
}

@Composable
private fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    state: ProductDetailState
) {
    when {
        state.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }
        /*state.product.id == null -> {
            // Empty or error
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text("No product data") }
        }*/
        else -> {
            val product = state.product
            val scroll = rememberScrollState()

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
                    .verticalScroll(state = scroll),
            ) {
                // Hero image
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = product.thumbnail,
                            contentDescription = product.title
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Title and brand
                Text(
                    text = product.title.orEmpty(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (!product.brand.isNullOrEmpty()) {
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(Modifier.height(8.dp))

                // Chips: category, stock, rating
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!product.category.isNullOrEmpty()) {
                        AssistChip(onClick = {}, label = { Text(product.category) })
                    }
                    if (product.stock != null) {
                        AssistChip(
                            onClick = {},
                            label = { Text("Stock: ${product.stock}") },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        )
                    }
                    if (product.rating != null) {
                        AssistChip(
                            onClick = {},
                            label = { Text("⭐ ${product.rating}") }
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Price and discount
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$ ${product.price ?: 0.0}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                    if (product.discountPercentage != null && product.discountPercentage > 0.0) {
                        Spacer(Modifier.width(12.dp))
                        AssistChip(onClick = {}, label = { Text("-${product.discountPercentage}%") })
                    }
                }

                Spacer(Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(Modifier.height(12.dp))

                // Description
                if (!product.description.isNullOrEmpty()) {
                    Text(
                        text = stringResource(Res.string.description),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(12.dp))
                }

                // Tags
                if (!product.tags.isNullOrEmpty()) {
                    Text(
                        text = stringResource(Res.string.tags),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        product.tags.forEach { tag ->
                            AssistChip(onClick = {}, label = { Text(tag) })
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                }

                // Gallery
                if (!product.images.isNullOrEmpty()) {
                    Text(
                        text = stringResource(Res.string.gallery),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        product.images.forEach { url ->
                            ElevatedCard { AsyncImage(model = url, contentDescription = null) }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                val availability = product.availabilityStatus ?: ""
                if (availability.isNotEmpty() || product.warrantyInformation != null || product.shippingInformation != null) {
                    Text(
                        text = stringResource(Res.string.information),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (availability.isNotEmpty()) Text("Status: $availability", style = MaterialTheme.typography.bodyMedium)
                        product.warrantyInformation?.let { Text("Warranty: $it", style = MaterialTheme.typography.bodyMedium) }
                        product.shippingInformation?.let { Text("Shipping: $it", style = MaterialTheme.typography.bodyMedium) }
                        product.returnPolicy?.let { Text("Return: $it", style = MaterialTheme.typography.bodyMedium) }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailScreenPrev() {
    MaterialTheme {
        ProductDetailScreen(
            state = ProductDetailState(
                product = ProductDetail(
                    id = 1,
                )
            )
        )
    }
}