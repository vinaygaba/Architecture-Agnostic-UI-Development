package com.droidconsf.architectureagnosticuidevelopment.ui.comicbooks.compose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeTextStyle
import androidx.ui.text.style.TextOverflow
import com.droidconsf.architectureagnosticuidevelopment.core.api.models.Comic
import com.droidconsf.architectureagnosticuidevelopment.ui.ComicbooksViewModel
import com.droidconsf.architectureagnosticuidevelopment.ui.common.statemachine.Event

@Composable
internal fun ComicDetailScreen(
    viewModel: ComicbooksViewModel,
    comic: Comic?,
    descriptionExpanded: Boolean?
) {
    comic?.let {
        Surface(color = Color(24, 24, 24)) {
            Container(modifier = Spacing(16.dp), alignment = Alignment.TopCenter) {
                FlexRow {
                    inflexible {
                        ComicImage(122.dp, 170.dp, getRandomImageResId())
                    }
                    expanded(1f) {
                        Column {
                            ComicTitleAndDescription(it, descriptionExpanded)
                            ShowMoreButton(viewModel, descriptionExpanded)
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun ComicTitleAndDescription(
    comic: Comic,
    descriptionExpanded: Boolean?
) {
    val maxLines = if (descriptionExpanded == true) 12 else 3
    Padding(left = 16.dp, right = 16.dp, bottom = 16.dp) {
        val textStyle = (+themeTextStyle { subtitle1 }).copy(color = Color.White)
        Text(comic.title, style = textStyle, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
    comic.description?.let { sub ->
        Padding(left = 16.dp, right = 16.dp) {
            val textStyle = (+themeTextStyle { caption }).copy(color = Color.White)
            Text(sub, style = textStyle, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
internal fun ShowMoreButton(
    viewModel: ComicbooksViewModel,
    descriptionExpanded: Boolean?
) {
    if (descriptionExpanded != true) {
        val buttonStyle = (+themeTextStyle { h6 }).copy(color = Color.White)
        Center {
            Padding(left = 16.dp, right = 16.dp, top = 16.dp) {
                Clickable(onClick = {
                    viewModel.triggerEvent(Event.View.ShowMoreDescription)
                }) {
                    Text(
                        text = "SHOW MORE",
                        style = buttonStyle
                    )
                }
            }
        }
    }
}