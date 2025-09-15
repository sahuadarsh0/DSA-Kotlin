class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size){
            return findMedianSortedArrays(nums2,nums1)
        }
        val m = nums1.size
        val n = nums2.size
        var low = 0
        var high = m
        val halfLen = (m+n +1)/2
        while (low <= high)
        {
            val partX = (low + high)/2
            val partY = halfLen - partX
            val maxLeftX = if (partX ==0) Int.MIN_VALUE else nums1[partX -1]
            val minRightX = if (partX ==m) Int.MAX_VALUE else nums1[partX]
            val maxLeftY = if (partY ==0) Int.MIN_VALUE else nums2[partY -1]
            val minRightY = if (partY ==n) Int.MAX_VALUE else nums2[partY]
            if(maxLeftX <= minRightY && maxLeftY <= minRightX){
                val maxLeft = max(maxLeftX,maxLeftY)
                return if ((m+n)%2==1){
                    maxLeft.toDouble()
                }else{
                    val minRight = min (minRightX,minRightY)
                    (maxLeft + minRight)/2.0
                }

            }else if(maxLeftX > minRightY ){
                high = partX - 1
            } else {
                low = partX+1
            }
            }
            throw IllegalArgumentException("ss")
    }
}