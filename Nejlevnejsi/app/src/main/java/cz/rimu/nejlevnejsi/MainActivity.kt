package cz.rimu.nejlevnejsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cz.rimu.nejlevnejsi.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.main_activity)
		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
					.replace(R.id.container, HomeFragment.newInstance())
					.commitNow()
		}
	}
}