package com.gs.panel.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints

@Composable
fun DynamicsRowWidget(modifier: Modifier = Modifier, space: Int = 8, content: @Composable () -> Unit) {
    val measurePolicy = MeasurePolicy { measurables, constraints ->
        val childrenSize = measurables.size
        var childrenSpace = 0
        val placeables = measurables.mapIndexed { index, child ->
            val placeable = child.measure(Constraints(0, constraints.maxWidth, 0, constraints.maxHeight))
            if (index != childrenSize - 1) {
                childrenSpace += placeable.width + space
            }
            placeable
        }
        childrenSpace -= space
        val placeableList = mutableListOf<Placeable>()
        if (childrenSpace > constraints.maxWidth) {
            var sumSpace = placeables[childrenSize - 1].width
            for (i in 0 until placeables.size - 1) {
                if (sumSpace + placeables[i].width <= constraints.maxWidth) {
                    placeableList.add(placeables[i])
                    sumSpace += placeables[i].width + space
                } else {
                    break
                }
            }
            placeableList.add(placeables[childrenSize - 1])
        } else {
            placeableList.addAll(placeables.subList(0, childrenSize - 1))
        }
        var xPosition = 0
        layout(constraints.minWidth, constraints.minHeight) {
            placeableList.forEach { placeable ->
                placeable.placeRelative(xPosition, 0)
                xPosition += placeable.width + space
            }
        }
    }
    Layout(content = content, modifier = modifier, measurePolicy = measurePolicy)
}