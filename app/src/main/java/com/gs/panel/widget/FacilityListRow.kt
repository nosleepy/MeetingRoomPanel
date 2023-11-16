package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
fun FacilityListRow(
    modifier: Modifier,
    facilityList: List<FacilityItem>,
    itemFillColor: Color,
    moreItemColor: Color,
    onMoreClick: () -> Unit = {},
) {
    if (facilityList.isNotEmpty()) {
        DynamicsRow(modifier = modifier) {
            facilityList.forEach { facilityItem ->
                if (facilityItem.confFacilityCode == "More") {
                    Text(
                        text = "More",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12))
                            .background(Color.White)
                            .clickable { onMoreClick() }
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