package com.example.ppapb_travelakuy.usermenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppapb_travelakuy.databinding.UsermenuHomeFragmentBinding
import com.example.ppapb_travelakuy.db.model.TravelForHome
import com.example.ppapb_travelakuy.listener.StationCrudListener
import com.example.ppapb_travelakuy.usersignin.MainActivity

class HomeFragment : Fragment() {
    private lateinit var binding: UsermenuHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsermenuHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crud = MainActivity.get() as StationCrudListener
        crud.getTravel()

        with(binding){

            crud.getAllTravel().observe(viewLifecycleOwner) {
                rvListTravel.apply {
                    adapter = ItemTravelForHomeAdapter(it, addHistory = {
                        val bundle = Bundle()
                        bundle.putString("stationOne", it.station_one)
                        bundle.putString("stationTwo", it.station_two)
                        bundle.putInt("price", it.price)
                        val intent = Intent(requireContext(), OrderDetailFragment::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    })
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }
    private fun listOfTravel(): List<TravelForHome> {
        return listOf()
    }
}