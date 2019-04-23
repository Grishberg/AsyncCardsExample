package com.grishberg.viewpagercard

class ViewPagerCard(
    val adapter: CustomPagerAdapter,
    val viewPager: CustomViewPager
) {
    fun prepare() {
        viewPager.adapter = adapter
    }
}