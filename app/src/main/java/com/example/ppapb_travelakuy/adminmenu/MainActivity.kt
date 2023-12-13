package com.example.ppapb_travelakuy.adminmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ppapb_travelakuy.R
import com.example.ppapb_travelakuy.databinding.AdminmenuActivityMainBinding
import com.example.ppapb_travelakuy.listener.StationCrudListener
import com.example.ppapb_travelakuy.usermenu.ItemTravelForHomeAdapter
import com.example.ppapb_travelakuy.usersignin.MainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AdminmenuActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminmenuActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val crud = MainActivity.get() as StationCrudListener
        crud.getTravel()

        with(binding) {

            crud.getAllTravel().observe(this@MainActivity) {
                rvTravelItem.apply {
                    adapter = ItemTravelForHomeAdapter(it, isAdmin = true, onClick = {
                        crud.deleteTravel(it.id)
                        Toast.makeText(this@MainActivity, "Delete Success", Toast.LENGTH_SHORT).show()
                    })
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
                }
            }

            btnAdd.setOnClickListener {
                crud.addTravel(
                    etStationOne.text.toString(),
                    etStationTwo.text.toString(),
                    etPrice.text.toString()
                )

            }
            button2.setOnClickListener {
                MainActivity.get().logout()
                finish()
            }
        }
    }
}