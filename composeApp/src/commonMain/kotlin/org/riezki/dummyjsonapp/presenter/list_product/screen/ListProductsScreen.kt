package org.riezki.dummyjsonapp.presenter.list_product.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.riezki.dummyjsonapp.domain.model.ProductSummary
import org.riezki.dummyjsonapp.presenter.list_product.component.ListProductComponent

/**
 * @author riezky maisyar
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListProductsScreen(
    modifier: Modifier = Modifier,
    products: List<ProductSummary> = emptyList(),
    columns: Int = 2,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    horizontalSpacing: Dp = 12.dp,
    verticalSpacing: Dp = 12.dp,
    // resolve image URL by product id (DummyJSON thumbnail uses /product-images/ but not present in domain model)
    imageUrl: (ProductSummary) -> String? = { null },
    onClick: (ProductSummary) -> Unit = {}
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(columns),
        verticalItemSpacing = verticalSpacing,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        contentPadding = contentPadding
    ) {
        items(products, key = { it.id }) { product ->
            ListProductComponent(
                product = product,
                imageUrl = imageUrl(product),
                onClick = { onClick(product) }
            )
        }
    }
}