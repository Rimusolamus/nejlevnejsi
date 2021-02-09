package cz.rimu.nejlevnejsi.ui.addOffer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import cz.rimu.nejlevnejsi.R


class AddOfferActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_offer)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        findViewById<ExtendedFloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Vypln' všechna pole prosim", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val point = LatLng(21.0, 57.0)
        mMap?.addMarker(MarkerOptions().position(point).title("my marker"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(point))
    }
}