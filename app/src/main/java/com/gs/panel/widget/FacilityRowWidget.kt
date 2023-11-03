package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.entity.FacilityItem

@Composable
fun FacilityRowWidget(
    modifier: Modifier,
    facilityList: List<FacilityItem>,
    itemFillColor: Color = Color(0xFF30831f),
    moreItemColor: Color = Color(0xFF00a645),
    onMoreClick: () -> Unit = {},
) {
    if (facilityList.isNotEmpty()) {
        DynamicsRowWidget(modifier = modifier) {
            facilityList.forEach { facilityItem ->
                if (facilityItem.confFacilityId == 0) {
                    Text(
                        text = "More",
                        modifier = Modifier
                            .clickable { onMoreClick() }
                            .clip(RoundedCornerShape(12))
                            .background(Color.White)
                            .padding(6.dp, 3.dp, 6.dp, 3.dp),
                        color = moreItemColor,
                        fontSize = 20.sp,)
                } else {
                    Text(
                        text = facilityItem.confFacilityName,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12))
                            .background(itemFillColor)
                            .padding(6.dp, 3.dp, 6.dp, 3.dp),
                        color = Color(0xFFecece1),
                        fontSize = 20.sp,)
                }
            }
        }
    } else {
        Box(modifier = modifier)
    }
}