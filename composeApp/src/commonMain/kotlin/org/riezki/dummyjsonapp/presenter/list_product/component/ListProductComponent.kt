package org.riezki.dummyjsonapp.presenter.list_product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.riezki.dummyjsonapp.domain.model.ProductSummary


/**
 * @author riezky maisyar
 */

@Composable
fun ListProductComponent(
    product: ProductSummary,
    imageUrl: String?,
    onClick: () -> Unit,
) {
    val minHeight = 140.dp
    val maxHeight = 220.dp

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = minHeight, max = maxHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null
            )
            /*var imageLoadedResultApp by remember {
                mutableStateOf<Result<Painter>?>(null)
            }

            val painter = rememberAsyncImagePainter(
                model = imageUrl,
                onSuccess = {
                    imageLoadedResultApp = if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                        Result.success(it.painter)
                    } else {
                        Result.failure(IllegalArgumentException("Image size is too small"))
                    }
                },
                onError = {
                    it.result.throwable.printStackTrace()
                    imageLoadedResultApp = Result.failure(it.result.throwable)
                }
            )

            Image(
                painter = painter,
                contentDescription = product.title
            )

            when (val result = imageLoadedResultApp) {
                null -> {
                    CircularProgressIndicator()
                }
                else -> {

                }
            }*/
        }

        // Content
        Box(modifier = Modifier.padding(12.dp)) {
            Column {
                Text(
                    text = product.title ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "ID: ${product.id}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Red
                )
                Text(
                    text = "$ ${product.price}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun ListProductComponentPrev() {
    MaterialTheme {
        ListProductComponent(
            product = ProductSummary(
                id = 1,
                title = "iPhone 9",
                price = 549.0
            ),
            imageUrl = null,
            onClick = {}
        )
    }
}