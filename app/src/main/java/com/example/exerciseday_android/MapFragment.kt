package com.example.exerciseday_android

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.data.remote.gym.AddressResponse
import com.example.exerciseday_android.data.remote.gym.GymMainResult
import com.example.exerciseday_android.data.remote.gym.GymRetrofitInterface
import com.example.exerciseday_android.data.remote.gym.GymService
import com.example.exerciseday_android.databinding.FragmentMapBinding
import com.example.exerciseday_android.ui.gym.main.GymMainRVAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.NaverMap.OnMapClickListener
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.android.synthetic.main.bottom_sheet_map_spinner.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MapFragment() : Fragment(), OnMapReadyCallback, GymView {

    lateinit var binding: FragmentMapBinding
    private var mapGymData = ArrayList<GymMainResult>()

    companion object {
        lateinit var naverMap: NaverMap
        var univLatLng: LatLng = LatLng(0.0, 0.0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        // 검색 버튼 클릭 시
        binding.mapSearchBtn.setOnClickListener {
            // 검색 페이지로 이동

        }


        // Spinner 누르면 Modal Bottom Sheet 등장
        // 대학교 Spinner
        val mapUnivBottomSheetView =
            layoutInflater.inflate(R.layout.bottom_sheet_map_spinner, binding.root, false)
        val mapUnivBottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialog)
        val mapUnivArray = resources.getStringArray(R.array.map_university_entries)
        mapUnivBottomSheetView.bottom_sheet_title_tv.text = "대학교 선택"
        mapUnivBottomSheetDialog.setContentView(mapUnivBottomSheetView)
        setBottomSheetView(
            mapUnivBottomSheetView,
            mapUnivArray,
            mapUnivBottomSheetDialog,
            binding.mapUniversitySpinner
        )

        binding.mapUniversitySpinner.setOnClickListener {
            mapUnivBottomSheetDialog.show()
        }

        // 정렬 Spinner
        val mapOrderBottomSheetView =
            layoutInflater.inflate(R.layout.bottom_sheet_map_spinner, binding.root, false)
        val mapOrderBottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialog)
        val mapOrderArray = resources.getStringArray(R.array.map_order_entries)
        mapOrderBottomSheetView.bottom_sheet_title_tv.text = "정렬"
        mapOrderBottomSheetDialog.setContentView(mapOrderBottomSheetView)
        setBottomSheetView(
            mapOrderBottomSheetView,
            mapOrderArray,
            mapOrderBottomSheetDialog,
            binding.mapOrderSpinner
        )

        binding.mapOrderSpinner.setOnClickListener {
            mapOrderBottomSheetDialog.show()
        }


        // Spinner 설정에 따른 지도 위치 이동
        binding.mapGymMapView.onCreate(savedInstanceState)
        binding.mapGymMapView.getMapAsync(this)


        // 데이터 리스트 생성 더미 데이터
        mapGymData.apply {
            add(
                GymMainResult(
                    0, "머슬PT",
                    "서울 노원구 광운로39 3층",
                    "~",
                    383,
                    univ = "광운대학교",
                    gymParking = true,
                    gymSauna = true,
                    gymCloths = true,
                    gymShower = true,
                    4.5f
                )
            )
            add(
                GymMainResult(
                    1, "하루운동 휘트니스",
                    "서울 노원구 광운로39 3층",
                    "~",
                    383,
                    univ = "광운대학교",
                    gymParking = false,
                    gymSauna = true,
                    gymCloths = true,
                    gymShower = true,
                    4.0f
                )
            )
        }


        // 헬스장 RecyclerView 어댑터와 데이터 리스트 연결
        binding.mapGymRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        getGym()

        val gymMainRVAdapter = GymMainRVAdapter(mapGymData)

        gymMainRVAdapter.setMyItemClickListener(object : GymMainRVAdapter.MyItemClickListener {
            override fun onItemClick(gymMainResult: GymMainResult) {
                TODO("Not yet implemented")
                // Item 클릭 시 헬스장 세부 페이지로 이동

            }
        })

        binding.mapGymRv.adapter = gymMainRVAdapter

        return binding.root
    }


    // Spinner 선택 설정
    private fun setBottomSheetView(
        bottomSheetView: View,
        arr: Array<String>,
        dialog: BottomSheetDialog,
        spinner: TextView
    ) {
        val bottomSheetListLayout: LinearLayout =
            bottomSheetView.findViewById(R.id.bottom_sheet_list_layout)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        val value = 16
        val dpValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            requireContext().resources.displayMetrics
        ).toInt()


        for (i in arr.indices) {
            // Spinner array 항목 수만큼 TextView 동적 생성
            val bottomSheetListTv = TextView(requireContext())

            bottomSheetListTv.layoutParams = layoutParams

            bottomSheetListTv.text = arr[i]
            bottomSheetListTv.gravity = Gravity.CENTER
            bottomSheetListTv.setTextSize(Dimension.SP, 17.0f)
            bottomSheetListTv.setTypeface(bottomSheetListTv.typeface, Typeface.NORMAL)
            bottomSheetListTv.setTextColor(resources.getColor(R.color.gray_950, null))
            bottomSheetListTv.setPadding(0, dpValue, 0, dpValue)
            bottomSheetListTv.setBackgroundResource(R.drawable.tv_gray50_to_gray300)

            bottomSheetListLayout.addView(bottomSheetListTv)

            // 클릭 시 해당 array 항목으로 Spinner 설정
            bottomSheetListTv.setOnClickListener {
                spinner.text = arr[i]
                dialog.dismiss()
                onMapReady(naverMap)    // 지도 위치 설정
                getGym()    // 헬스장 불러오기
            }
        }

        // 상단 close bar 버튼 누르면 닫기
        val closeBtn = bottomSheetView.findViewById<ImageButton>(R.id.bottom_sheet_close_btn)
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onMapReady(naverMap: NaverMap) {
        MapFragment.naverMap = naverMap

        // 내장 위치 추적 기능 사용
//        naverMap.locationSource = locationSource

        // UI 컨트롤 재배치
        val uiSettings: UiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = false
        uiSettings.isScaleBarEnabled = false
        uiSettings.isZoomControlEnabled = true

        val leftValue = 25
        val bottomValue = 8
        uiSettings.setLogoMargin(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                leftValue.toFloat(),
                requireContext().resources.displayMetrics
            ).toInt(), 0, 0, TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                bottomValue.toFloat(),
                requireContext().resources.displayMetrics
            ).toInt()
        )
        binding.mapGymMapScaleBar.map = naverMap


        // 대학 주소
        val univAddressArray = resources.getStringArray(R.array.map_university_address_entries)

        // Spinner 설정된 대학으로 위치 설정
        val mapUnivArray = resources.getStringArray(R.array.map_university_entries)

        if (binding.mapUniversitySpinner.text.equals(mapUnivArray[0])) {
            // 광운대
            getLatLng(univAddressArray[0])
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[1])) {
            // 서울여대
            getLatLng(univAddressArray[1])
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[2])) {
            // 경희대학교
            getLatLng(univAddressArray[2])
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[3])) {
            // 동덕여자대학교
            getLatLng(univAddressArray[3])
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[4])) {
            //한국외국어대학교
            getLatLng(univAddressArray[4])
        }


        // 정보 창과 어댑터 연결
        val infoWindow = InfoWindow()
        val adapter = PointAdapter(requireContext(), binding.mapGymMapView)
        infoWindow.adapter = adapter

        // 핼스장 표시 마커
        val gymMarker = Marker()

//        val gymMarker = Marker().apply {
//            setOnClickListener {
//                // 정보 창 표시
//                infoWindow.open(this)
//
//                true
//            }
//        }

        // 헬스장 다중 마커
//        val gymMarkers = ArrayList<Marker>()
//
//        gymMarkers.forEach { marker ->
//            marker.icon = OverlayImage.fromResource(R.drawable.ic_location_gym)
//            marker.map = naverMap
//        }

//        for (i in mapGymData.indices) {
//            // gym list 항목 수만큼 Marker 동적 생성
//            gymMarkers[i].position = LatLng(35.0,35.0)
//            gymMarkers[i].icon = OverlayImage.fromResource(R.drawable.ic_location_gym)
//            gymMarkers[i].map = naverMap
//
//            gymMarkers[i].setOnClickListener {
//                // 위치 이동
//                val position = LatLng(
//                    37.62107392,
//                    127.05892492
//                )
//                val cameraUpdate = CameraUpdate.scrollTo(position)
//                    .animate(CameraAnimation.Easing)
//                naverMap.moveCamera(cameraUpdate)
//
//                // 정보 창 표시
//                infoWindow.open(gymMarkers[i])
//
//                true
//            }
//        }


        // 임시 위치 설정 -> 헬스장 위치 불러와서 위치 설정 필요
        gymMarker.position = LatLng(
            37.62107392,
            127.05892492
        )
        gymMarker.icon = OverlayImage.fromResource(R.drawable.ic_location_gym)
        gymMarker.map = naverMap



        gymMarker.setOnClickListener {
            // 위치 이동
            val position = LatLng(
                37.62107392,
                127.05892492
            )
            val cameraUpdate = CameraUpdate.scrollTo(position)
                .animate(CameraAnimation.Easing)
            naverMap.moveCamera(cameraUpdate)

            // 정보 창 표시
            infoWindow.open(gymMarker)

            /* 헬스장 마커 클릭 시 헬스장 정보(이름) 띄우고
            리사이클러뷰에 해당 헬스장만 불러오기 */


            true
        }


        // 이동 위치 업데이트
        naverMap.onMapClickListener =
            OnMapClickListener { PointF, latLng ->
                val latitude = latLng.latitude
                val longitude = latLng.longitude
                val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
                    .animate(CameraAnimation.Easing)
                naverMap.moveCamera(cameraUpdate)

                // 정보 창 닫기
                infoWindow.close()
            }
    }


    private fun getGym() {
        val univ = binding.mapUniversitySpinner.text.toString()

        Log.d("univ", univ)
        val gymService = GymService()
        gymService.setGymView(this)

        gymService.getGym(univ)
    }

    override fun onGymSuccess(result: ArrayList<GymMainResult>) {
        mapGymData = result
    }

    override fun onGymFailure(code: Int, message: String) {
        Log.d("GEY_GYM/FAILURE", "$code / $message")
    }

    // 주소 -> 위도, 경도 구하기
    private fun getLatLng(address: String): LatLng {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://naveropenapi.apigw.ntruss.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: GymRetrofitInterface = retrofit.create(GymRetrofitInterface::class.java)

        server.searchAddress(address).enqueue(object : Callback<AddressResponse> {
            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {
                Log.d("GET_LATLNG/SUCCESS", response.body().toString())

                val resp: AddressResponse = response.body()!!

                when (resp.status) {
                    "OK" -> {
                        Log.d("REAL", "$resp.addresses[0].x / $resp.addresses[0].y")

                        var camPos = CameraPosition(
                            LatLng(resp.addresses[0].y, resp.addresses[0].x),
                            15.0
                        )
                        naverMap.cameraPosition = camPos

                        // 대학 표시 마커
                        val univMarker = Marker()
                        univMarker.position = LatLng(
                            naverMap.cameraPosition.target.latitude,
                            naverMap.cameraPosition.target.longitude
                        )
                        univMarker.icon = OverlayImage.fromResource(R.drawable.ic_location_univ)
                        univMarker.map = naverMap
                    }
                    else -> Log.d("GET_LATLNG/FAILURE", resp.status)
                }
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                Log.d("GET_LATLNG/FAILURE", t.toString())
            }
        })

        return MapFragment.univLatLng
    }

    override fun onStart() {
        super.onStart()
        binding.mapGymMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapGymMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapGymMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapGymMapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.mapGymMapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapGymMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapGymMapView.onLowMemory()
    }


}

class PointAdapter(context: Context, parent: ViewGroup) : InfoWindow.ViewAdapter() {

    /* gymName Data 받아와야함 */
    private val mContext: Context
    private val mParent: ViewGroup

    init {
        mContext = context
        mParent = parent
    }

    override fun getView(p0: InfoWindow): View {
        val view =
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_gym_map_info_window, mParent, false) as View

        val mapGymPointInfoName =
            view.findViewById<View>(R.id.item_gym_map_info_window_name_tv) as TextView

        mapGymPointInfoName.text = "엔비짐 PT"  // gymName


        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        val value = 8
        val dpValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            mContext.resources.displayMetrics
        ).toInt()

        layoutParams.setMargins(0, 0, 0, dpValue)

        view.layoutParams = layoutParams

        return view
    }
}

interface GymView {
    fun onGymSuccess(result: ArrayList<GymMainResult>)
    fun onGymFailure(code: Int, message: String)
}