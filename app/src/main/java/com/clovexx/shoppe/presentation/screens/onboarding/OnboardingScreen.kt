package com.clovexx.shoppe.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clovexx.shoppe.navigation.Navigation
import com.clovexx.shoppe.presentation.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { viewModel.pages.size })
    val scope = rememberCoroutineScope()
    val backgroundRes = viewModel.pages.getOrNull(pagerState.currentPage)?.backgroundRes

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(viewModel.pages) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (abs(dragAmount) > 80) {
                        scope.launch {
                            if (dragAmount < 0 && pagerState.currentPage < viewModel.pages.lastIndex) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else if (dragAmount > 0 && pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                }
            }
    ) {
        BubblesBackground(backgroundRes!!)

        androidx.compose.foundation.pager.HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) { pageIdx ->
            val page = viewModel.pages[pageIdx]
            val isLast = pageIdx == viewModel.pages.lastIndex

            Box(
                Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 70.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .width(330.dp)
                            .height(600.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(16.dp),
                    ) {
                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = painterResource(page.imageRes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(340.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 30.dp,
                                            topEnd = 30.dp,
                                            bottomStart = 0.dp,
                                            bottomEnd = 0.dp
                                        )
                                    )
                            )
                            Spacer(Modifier.height(24.dp))
                            Text(
                                page.title,
                                color = Color(0xFF202020),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                page.description,
                                color = Color.Black,
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                            )
                            Spacer(Modifier.weight(1f))
                            if (isLast) {
                                Button(
                                    onClick = {
                                        navController.navigate(Navigation.Home.route) {
                                            popUpTo(Navigation.Onboarding.route) { inclusive = true }
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 30.dp)
                                        .height(46.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF004CFF),
                                        contentColor = Color(0xFFF3F3F3)
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text("Готово", fontSize = 20.sp)
                                }
                                Spacer(Modifier.height(18.dp))
                            } else {
                                Spacer(Modifier.height(36.dp))
                            }
                        }
                    }
                }
            }
        }
        OnboardingDots(
            pageCount = viewModel.pages.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 38.dp)
        )
    }
}

@Composable
fun OnboardingDots(pageCount: Int, selectedIndex: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(22.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        repeat(pageCount) { idx ->
            val animatedColor by animateColorAsState(
                targetValue = if (idx == selectedIndex) Color(0xFF004CFF) else Color(0xFFC7D6FB),
                label = "DotColor"
            )
            val animatedSize by animateFloatAsState(
                targetValue = if (idx == selectedIndex) 20f else 14f,
                label = "DotSize"
            )
            Box(
                Modifier
                    .size(animatedSize.dp)
                    .clip(CircleShape)
                    .background(animatedColor)
            )
        }
    }
}

@Composable
fun BubblesBackground(@DrawableRes resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}
