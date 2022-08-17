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
import com.example.exerciseday_android.data.remote.gym.GymMainResult
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


class MapFragment : Fragment(), OnMapReadyCallback, GymView {

    lateinit var binding: FragmentMapBinding
    private var mapGymData = ArrayList<GymMainResult>()

    companion object {
        lateinit var naverMap: NaverMap
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
                    gymShower = true
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
                    gymShower = true
                )
            )
        }


        // RecyclerView 어댑터와 데이터 리스트 연결
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
            bottomSheetListTv.setBackgroundResource(R.drawable.tv_white_to_gray300)

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


    // https://youngest-programming.tistory.com/659 참고
    override fun onMapReady(naverMap: NaverMap) {
        MapFragment.naverMap = naverMap

//        val options = NaverMapOptions()
//            .camera(CameraPosition(LatLng(37.61979722, 127.05842977), 15.0))
//            .mapType(NaverMap.MapType.Basic)

//        val mapFragment = MapView.newInstance(options)

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


        // 광운대로 임시 시작 위치 설정. 추후 자기 위치에서 가까운 대학교로 위치 변경
        var camPos = CameraPosition(
            LatLng(37.61979722, 127.05842977),
            15.0
        )


        // Spinner 설정된 대학으로 위치 설정
        val mapUnivArray = resources.getStringArray(R.array.map_university_entries)

        if (binding.mapUniversitySpinner.text.equals(mapUnivArray[0])) {
            // 광운대
            camPos = CameraPosition(
                LatLng(37.61979722, 127.05842977),
                15.0
            )
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[1])) {
            // 서울여대
            camPos = CameraPosition(
                LatLng(37.62803063, 127.09042315),
                15.0
            )
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[2])) {
            // 경희대학교
            camPos = CameraPosition(
                LatLng(37.59644894, 127.05250817),
                15.0
            )
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[3])) {
            // 동덕여자대학교
            camPos = CameraPosition(
                LatLng(37.60683620, 127.04197218),
                15.0
            )
        } else if (binding.mapUniversitySpinner.text.equals(mapUnivArray[4])) {
            //한국외국어대학교
            camPos = CameraPosition(
                LatLng(37.59730773, 127.05828027),
                15.0
            )
        }

        naverMap.cameraPosition = camPos

        // 대학 표시 마커
        val univMarker = Marker()
        univMarker.position = LatLng(
            naverMap.cameraPosition.target.latitude,
            naverMap.cameraPosition.target.longitude
        )
        univMarker.icon = OverlayImage.fromResource(R.drawable.ic_location_univ)
        univMarker.map = naverMap


        // 정보 창과 어댑터 연결
        val infoWindow = InfoWindow()
        val adapter = PointAdapter(requireContext(), binding.mapGymMapView)
        infoWindow.adapter = adapter

        // 핼스장 표시 마커
        val gymMarker = Marker().apply {
            setOnClickListener {
                // 정보 창 표시
                infoWindow.open(this)

                false
            }
        }

        // 임시 위치 설정 -> 헬스장 위치 불러와서 위치 설정 필요
        gymMarker.position = LatLng(
            37.62107392,
            127.05892492
        )
        gymMarker.icon = OverlayImage.fromResource(R.drawable.ic_location_gym)
        gymMarker.map = naverMap


        // 지도를 클릭하면 정보 창을 닫음
//        naverMap.setOnMapClickListener { _, _ ->
//            infoWindow.close()
//        }

//        gymMarker.onClickListener = Overlay.OnClickListener {
//            // 정보 창 표시
//            infoWindow.open(gymMarker)

////            false
//        }


        /* 헬스장 마커 클릭 시 헬스장 정보(이름) 띄우고
        리사이클러뷰에 해당 헬스장만 불러오기 */


        // 이동 위치 업데이트
        naverMap.onMapClickListener =
            OnMapClickListener { PointF, latLng ->
//                if (gymMarker.position != LatLng(latLng.latitude, latLng.longitude)) {
//                    infoWindow.close()
//                }
                val latitude = latLng.latitude
                val longitude = latLng.longitude
                val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
                    .animate(CameraAnimation.Easing)
                naverMap.moveCamera(cameraUpdate)
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


    // 좌표 -> 주소 변환
//    private fun getAddress(lat: Double, lng: Double): String {
//        val geoCoder = Geocoder(requireContext(), Locale.KOREA)
//        val address: ArrayList<Address>
//        var addressResult = "주소를 가져 올 수 없습니다."
//        try {
//            // 세 번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
//            // 한 좌표에 대해 두 개 이상의 이름이 존재할 수 있기에 주소 배열을 리턴받기 위해 최대갯수 설정
//            address = geoCoder.getFromLocation(lat, lng, 1) as ArrayList<Address>
//            if (address.size > 0) {
//                // 주소 받아오기
//                val currentLocationAddress = address[0].getAddressLine(0)
//                    .toString()
//                addressResult = currentLocationAddress
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        return addressResult
//    }

}

class PointAdapter(context: Context, parent: ViewGroup) : InfoWindow.ViewAdapter() {

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