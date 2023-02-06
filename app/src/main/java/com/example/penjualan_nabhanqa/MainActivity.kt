package com.example.penjualan_nabhanqa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.penjualan_nabhanqa.room.penjualan
import com.example.penjualan_nabhanqa.room.tbbarang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var Adapterbrg: AdapterActivity
    val db by lazy { penjualan(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setUprecyclerView()
    }
    override fun onStart() {
        super.onStart()
        loadbar()
    }
    fun loadbar(){
        CoroutineScope(Dispatchers.IO).launch {
            val barang= db.TBBRGDAO().tampilsemua()
            Log.d("MainActivity", "dbResponse:$barang")
            withContext(Dispatchers.Main) {
                Adapterbrg.setData(barang)
            }
        }
    }

    private fun halEdit() {
        btnInput.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }
        fun intentEdit(tbbar: Int, intentType: Int) {
            startActivity(
                Intent(applicationContext, EditActivity::class.java)
                    .putExtra("intent_id", tbbar)
                    .putExtra("intent_type", intentType)
            )
        }


    private fun setUprecyclerView() {
        Adapterbrg = AdapterActivity(arrayListOf(), object : AdapterActivity.onAdapterListener {
            override fun onClick(tbbar: tbbarang) {
                //read detail note
                intentEdit(tbbar.id, Constant.TYPE_READ)
            }

            override fun onUp(tbbar: tbbarang) {
                intentEdit(tbbar.id, Constant.TYPE_UPDATE)
            }

            override fun onDelt(tbbar: tbbarang) {
                deleteDialog(tbbar)
            }

        })
    }
    private fun deleteDialog(tbbar: tbbarang) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("YAKIN MAU HAPUS ${tbbar.nmBrg}?")
            setNegativeButton("Batal"){ dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus"){ dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.TBBRGDAO().delTbbrg(tbbar)
                    loadbar()
                }
            }
        }
        alertDialog.show()


    }
}