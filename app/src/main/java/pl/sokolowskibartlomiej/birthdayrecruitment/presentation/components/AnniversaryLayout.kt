package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.sokolowskibartlomiej.birthdayrecruitment.R
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.BirthdayTheme
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme.BirthdayRecruitmentTheme
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme.getColorsForTheme
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.utils.getDrawableId
import java.util.Date
import java.util.concurrent.TimeUnit

@Composable
fun AnniversaryLayout(birthday: Birthday) {
    val context = LocalContext.current
    val colors = getColorsForTheme(birthday.theme)
    val backgroundImage = when (birthday.theme) {
        BirthdayTheme.PELICAN -> R.drawable.bg_pelican
        BirthdayTheme.FOX -> R.drawable.bg_fox
        BirthdayTheme.ELEPHANT -> R.drawable.bg_elephant
        BirthdayTheme.UNKNOWN -> null
    }
    var areYearsShowed by remember { mutableStateOf(false) }
    val number = remember(birthday.birthDate) {
        val diff = Date().time - birthday.birthDate.time
        val days = TimeUnit.MILLISECONDS.toDays(diff)
        if (diff < 0) 0
        else if (days > 365) {
            areYearsShowed = true
            (days / 365).toInt()
        } else {
            areYearsShowed = false
            (days / 30.4).toInt()
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colors[0])
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 183.dp) // 148 + 20 (nanit) + 15 (padding)
                .size(200.dp)
                .background(color = colors[2], shape = CircleShape)
                .border(width = 7.dp, color = colors[1], shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.photo_placeholder),
                colorFilter = ColorFilter.tint(color = colors[1]),
                contentDescription = null,
            )
        }
        backgroundImage?.let {
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .aspectRatio(0.61f)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 383.dp) // 183 + 200
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(id = R.string.anniversary_title, birthday.name).uppercase(),
                color = Color(0xFF394562),
                fontSize = 21.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                modifier = Modifier.width(252.dp)
            )
            Spacer(modifier = Modifier.height(13.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.swirls),
                    contentDescription = null
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    number.toString().forEach {
                        Image(
                            painter = painterResource(id = context.getDrawableId("number$it")),
                            contentDescription = null,
                            modifier = Modifier.height(88.dp)
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.swirls),
                    contentDescription = null,
                    modifier = Modifier.rotate(180f)
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(
                    id = if (areYearsShowed) R.string.years_old else R.string.months_old
                ).uppercase(),
                color = Color(0xFF394562),
                fontSize = 21.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.nanit),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 148.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnniversaryPelicanPreview() {
    BirthdayRecruitmentTheme {
        AnniversaryLayout(
            birthday = Birthday(
                name = "Cristiano Ronaldo",
                theme = BirthdayTheme.PELICAN,
                birthDate = Date(1706871600000) //02.02.2024
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnniversaryFoxPreview() {
    AnniversaryLayout(
        birthday = Birthday(
            name = "Cristiano Ronaldo",
            theme = BirthdayTheme.FOX,
            birthDate = Date(1685700000000) // 02.06.2023
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AnniversaryElephantPreview() {
    AnniversaryLayout(
        birthday = Birthday(
            name = "Cristiano Ronaldo",
            theme = BirthdayTheme.ELEPHANT,
            birthDate = Date(1580641200000) // 02.02.2020
        )
    )
}