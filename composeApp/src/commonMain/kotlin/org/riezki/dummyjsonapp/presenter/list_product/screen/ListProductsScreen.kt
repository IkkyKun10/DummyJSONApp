package org.riezki.dummyjsonapp.presenter.list_product.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowLeft
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.riezki.dummyjsonapp.domain.model.ProductSummary
import org.riezki.dummyjsonapp.presenter.event.AppEvent.ProductsEvent
import org.riezki.dummyjsonapp.presenter.list_product.component.ListProductComponent

/**
 * @author riezky maisyar
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListProductsScreen(
    modifier: Modifier = Modifier,
    products: List<ProductSummary> = emptyList(),
    onEvent: (ProductsEvent) -> Unit,
    columns: Int = 2,
    contentPadding: PaddingValues = PaddingValues(vertical = 32.dp, horizontal = 14.dp),
    horizontalSpacing: Dp = 12.dp,
    verticalSpacing: Dp = 12.dp,
    onClick: (ProductSummary) -> Unit = {}
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(columns),
        verticalItemSpacing = verticalSpacing,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        contentPadding = contentPadding
    ) {
        items(
            products,
            key = { it.id ?: 0 }
        ) { product ->
            ListProductComponent(
                product = product,
                imageUrl = product.images?.firstOrNull(),
                onClick = { onClick(product) }
            )
        }
        
        item(span = StaggeredGridItemSpan.FullLine) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onEvent(ProductsEvent.OnPreviousProducts)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowLeft,
                        contentDescription = "Previous",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }

                Spacer(Modifier.width(12.dp))

                IconButton(
                    onClick = {
                        onEvent(ProductsEvent.OnNextProducts)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
                        contentDescription = "Next",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
        }
    }
}