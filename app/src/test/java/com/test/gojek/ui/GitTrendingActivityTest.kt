package com.test.gojek.ui

import android.content.Context
import android.os.Build
import android.widget.TextView
import com.test.gojek.GojekApplication
import com.test.gojek.R
import com.test.gojek.ui.viemodel.TrendingViewModel
import kotlinx.android.synthetic.main.fragment_no_network.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O],application = GojekApplication::class)
class GitTrendingActivityTest {
    var context: Context? = null
    @Mock
    var model: TrendingViewModel? = null
    lateinit var noNetworkFragment: NoNetworkFragment
    lateinit var trendingListFragment: TrendingListFragment
    lateinit var controller: ActivityController<GitTrendingActivity>
    @Before
    fun setup() {
        context = RuntimeEnvironment.systemContext
        noNetworkFragment = NoNetworkFragment()
        controller =
            Robolectric.buildActivity<GitTrendingActivity>(GitTrendingActivity::class.java).create()
                .start().resume()

    }

    @Test
    fun shouldNotNull() {
        Assert.assertNotNull(controller.get())
        Assert.assertTrue(controller.get() is GitTrendingActivity)
    }

    @Test
    fun validateTitleContent() {
        val appNameTextView: TextView = controller.get().findViewById(R.id.toolbar_title)
        Assert.assertEquals("Trending", appNameTextView.text.toString())
    }

    @Test
    fun validateNoInternet_Fragment() {
        val title = noNetworkFragment.errorTitle
        assertEquals(
            controller.get().getResources().getString(R.string.something_went_wrong),
            title.text.toString()
        )
        val description =
            noNetworkFragment.errorDescription
        assertEquals(
            controller.get().getResources().getString(R.string.an_alien_is_probably_blocking_your_signal),
            description.text.toString()
        )
        val errorImage =
            noNetworkFragment.errorImage
        val drawableResId = Shadows.shadowOf(errorImage.drawable).createdFromResId
        Assert.assertEquals(
            R.drawable.nointernet_connection.toLong(),
            drawableResId.toLong()
        )
        val retryBtn = noNetworkFragment.retry
        assertEquals(
            controller.get().getResources().getString(R.string.retry),
            retryBtn.text.toString()
        )
        retryBtn.performLongClick()
    }


}