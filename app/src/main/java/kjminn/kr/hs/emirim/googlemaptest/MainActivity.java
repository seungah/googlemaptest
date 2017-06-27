package kjminn.kr.hs.emirim.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.839269, 128.631892), 17));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                loc_mark = new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).position(latLng, 100f, 100f);
                googleMap.addGroundOverlay(loc_mark);
            }
        });


    }

    public static final int ITEM_SATELLITE = 1;
    public static final int ITEM_NORMAL = 2;
    public static final int ITEM_COEXMALL = 3;
    public static final int ITEM_IPARKMALL = 4;
    public static final int ITEM_MARK_CLEAR = 5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0, ITEM_SATELLITE, 0, "위성 지도");
        menu.add(0, ITEM_NORMAL, 0, "일반 지도");
        SubMenu hotMenu = menu.addSubMenu("핫 플레이스");
        hotMenu.add(0, ITEM_COEXMALL, 0, "코엑스몰");
        hotMenu.add(0, ITEM_IPARKMALL, 0, "아이파크몰");
        menu.add(0, ITEM_MARK_CLEAR, 0, "위치아이콘제거");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case ITEM_SATELLITE:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NORMAL:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_COEXMALL:
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.513287, 127.057824), 17));
            case ITEM_IPARKMALL:
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.529084, 126.964055), 17));
            case ITEM_MARK_CLEAR:
                googleMap.clear();
                return true;
        }
        return false;
    }

}
