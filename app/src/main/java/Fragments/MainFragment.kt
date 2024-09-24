package Fragments

import Adapters.VpAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.helloweatherapi.databinding.FragmentMainBinding
import android.Manifest
import android.widget.TableLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {
    private val fvist = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance()
    )
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var bindingClass: FragmentMainBinding
    private val tList = listOf(
        "Hours",
        "Days")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentMainBinding.inflate(inflater, container, false)
        return bindingClass.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        checkPermission()
        init()

    }

    private fun init() = with (bindingClass){
        val adapter = VpAdapter(activity as FragmentActivity,fvist)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout,vp){
           tab,poz-> tab.text = tList[poz]

        }.attach()


    }
    private fun permissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity,"permission is $it",Toast.LENGTH_LONG)
        }
    }
    private fun checkPermission(){ // передаём в функцию Extensions чтобы она проверила, чтобы у
        // нас было разрешение на определение локации
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}