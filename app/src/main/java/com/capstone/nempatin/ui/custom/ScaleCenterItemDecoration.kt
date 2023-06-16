package com.capstone.nempatin.ui.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class ScaleCenterItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutManager = parent.layoutManager as LinearLayoutManager
        val lp = view.layoutParams as RecyclerView.LayoutParams
        val position = lp.viewAdapterPosition

        val scaleFactor = when {
            position == layoutManager.findFirstCompletelyVisibleItemPosition() -> 1f  // Center item
            layoutManager.findFirstCompletelyVisibleItemPosition() - position == 1 || position - layoutManager.findFirstCompletelyVisibleItemPosition() == 1 -> 0.7f  // Items next to center
            else -> 0.5f  // Other items
        }

        view.scaleX = scaleFactor
        view.scaleY = scaleFactor
    }
}

class ScaleCenterSnapHelper : LinearSnapHelper() {
    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val position = layoutManager.getPosition(centerView)
        var targetPosition = -1
        if (layoutManager.canScrollHorizontally()) {
            targetPosition = if (velocityX < 0) {
                position - 1
            } else {
                position + 1
            }
        }
        if (layoutManager.canScrollVertically()) {
            targetPosition = if (velocityY < 0) {
                position - 1
            } else {
                position + 1
            }
        }
        val firstItem = 0
        val lastItem = layoutManager.itemCount - 1
        targetPosition = Math.max(firstItem, Math.min(lastItem, targetPosition))
        return targetPosition
    }
}
