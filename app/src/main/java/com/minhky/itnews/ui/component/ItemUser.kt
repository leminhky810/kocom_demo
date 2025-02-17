package com.minhky.itnews.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.minhky.itnews.R
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.model.User
import com.minhky.itnews.network.model.UserResponse

@Composable
fun ItemUser(modifier: Modifier = Modifier, item : User) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
        ) {
            val (avatar, userName) = createRefs()
            Box(modifier = Modifier
                .constrainAs(avatar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)

                    start.linkTo(parent.start)
                }) {
                Card(
                    shape = RoundedCornerShape(5.dp),
                ) {
                    AsyncImage(
                        model = item.avatarUrl, // Replace with your image resource
                        contentDescription = "Rounded Corner Image",
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFFe3dede))
                            .padding(2.dp)
                            .clip(CircleShape), // Rounded corners with 16dp radius
                        contentScale = ContentScale.FillBounds // Scale the image to fill the bounds
                    )
                }
            }

            Column(modifier = Modifier
                .constrainAs(userName) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(avatar.end, margin = 10.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints

                }) {
                Text(item.name?:"", textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.height(5.dp))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(), // Full-width line
                    thickness = 0.5.dp, // Line thickness
                    color = Color.Gray // Line color
                )
                Spacer(modifier = Modifier.height(5.dp))
                HyperlinkText(
                    Modifier,
                    "LinkedProfile",
                    linkText = listOf("LinkedProfile"),
                    hyperlinks = listOf(item.linkedURL?:"")
                )
            }

        }
    }
}

