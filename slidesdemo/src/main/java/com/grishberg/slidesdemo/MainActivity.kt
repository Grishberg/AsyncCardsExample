package com.grishberg.slidesdemo

import android.graphics.Point
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.grishberg.horizontalcards.SlideCardData
import com.grishberg.horizontalcards.SlidesView
import com.grishberg.viewpagercard.CustomPagerAdapter
import com.grishberg.viewpagercard.CustomViewPager
import com.grishberg.viewpagercard.ViewPagerCard


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val content = findViewById<FrameLayout>(R.id.content)
        //createSlidesAsync(content)

        createViewPager(content)
    }

    private fun createViewPager(content: ViewGroup) {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val rvHeight = resources.getDimensionPixelSize(R.dimen.slidesHeight)

        object : AsyncTask<Void, Void, View>() {
            override fun doInBackground(vararg params: Void?): View {
                val viewPager = CustomViewPager(this@MainActivity)
                val adapter = CustomPagerAdapter(this@MainActivity)
                val card = ViewPagerCard(adapter, viewPager)

                card.prepare()

                viewPager.measure(
                    View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(rvHeight, View.MeasureSpec.EXACTLY)
                )
                return viewPager
            }

            override fun onPostExecute(result: View) {

                content.addView(result)
            }
        }.execute()
    }

    private fun createSlidesAsync(content: ViewGroup) {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val rvHeight = resources.getDimensionPixelSize(R.dimen.slidesHeight)

        object : AsyncTask<Void, Void, View>() {
            override fun doInBackground(vararg params: Void?): View {
                val slidesView = SlidesView(this@MainActivity)
                slidesView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                slidesView.bindWithData(createData())
                slidesView.measure(
                    View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(rvHeight, View.MeasureSpec.EXACTLY)
                )
                return slidesView
            }

            override fun onPostExecute(result: View) {

                content.addView(result)
            }
        }.execute()
    }

    private fun createData(): List<SlideCardData> {
        val data = ArrayList<SlideCardData>()
        for (i in 0..10) {
            data.add(SlideCardData("title $i", "description $i"))
        }
        return data
    }
}
