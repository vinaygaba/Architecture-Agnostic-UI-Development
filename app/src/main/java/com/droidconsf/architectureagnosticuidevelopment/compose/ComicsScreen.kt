package com.droidconsf.architectureagnosticuidevelopment.compose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeTextStyle
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import com.droidconsf.architectureagnosticuidevelopment.api.models.Comic
import com.droidconsf.architectureagnosticuidevelopment.api.models.ComicThumbnail
import com.droidconsf.architectureagnosticuidevelopment.statemachine.ViewState

@Composable
fun ComicsScreen(state: ViewState) {
    MaterialTheme {
        VerticalScroller {
            Column(crossAxisSize = LayoutSize.Expand) {
                when (state) {
                    is ViewState.Loading -> {
                        FlexColumn {
                            expanded(1f) {
                                Center {
                                    CircularProgressIndicator(color = Color.Cyan)
                                }
                            }
                        }
                    }
                    is ViewState.ShowingContent -> {
                        state.comics.forEach { comic ->
                            ComicRow(comic)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComicRow(comic: Comic) {
    Surface(color = Color(24,24,24)) {
        Container(modifier = Spacing(16.dp)) {
            FlexRow {
                inflexible {
                    ComicImage()
                }
                expanded(1f) {
                    TitleSubtitleColumn(comic.title, comic.description)
                }
            }
        }
    }
}

@Composable
fun ComicImage() {
    Surface(color = Color(170,173,196)) {
        Container(height = 120.dp, width = 72.dp) {
            // TODO(vinay): Replace with image
        }
    }
}

@Composable
fun TitleSubtitleColumn(title: String, subtitle: String?) {
    Column {
        Padding(left = 16.dp, right = 16.dp, bottom = 16.dp) {
            val textStyle = (+themeTextStyle { subtitle1 }).copy(color = Color.White)
            Text(title, style = textStyle, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        subtitle?.let { sub ->
            Padding(left = 16.dp, right = 16.dp) {
                val textStyle = (+themeTextStyle { caption }).copy(color = Color.White)
                Text(sub, style = textStyle, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ComicsScreen(
        ViewState.ShowingContent(listOf(
            Comic(id = 1, description = "This is dope", issueNumber = 4567,
                title = "Comic Title that is fairly long to test if maxLines logic is working",
                thumbnail = ComicThumbnail(extension = "png",
                    path = ""))
        ))
    )
}