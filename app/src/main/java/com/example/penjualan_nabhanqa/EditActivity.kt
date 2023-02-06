package com.example.penjualan_nabhanqa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.penjualan_nabhanqa.room.penjualan
import com.example.penjualan_nabhanqa.room.tbbarang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { penjualan(this) }
    private var tbbrgid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setUpView()
        tombolperintah()
        tbbrgid = intent.getIntExtra("intent_nis", tbbrgid)
        Toast.makeText(this, tbbrgid.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setUpView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE

            }
            Constant.TYPE_READ -> {
                btnSimpan.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                EtId.visibility = View.GONE
                tampildataid()
            }
            Constant.TYPE_UPDATE -> {
                btnSimpan.visibility = View.GONE
                EtId.visibility = View.GONE
                tampildataid()

            }
        }
    }
    fun tombolperintah() {
        btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.TBBRGDAO().addTbbrg(
                    tbbarang(
                        EtId.text.toString().toInt(),
                        EtNm.text.toString(),
                        EtHrg.text.toString().toInt(),
                        EtST.text.toString().toInt(),
                    )
                    )
                finish()
            }
        }
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.TBBRGDAO().upTbbrg(
                    tbbarang(tbbrgid,
                        //EtId.text.toString(),
                        EtNm.text.toString(),
                        EtHrg.text.toString().toInt(),
                        EtST.text.toString().toInt())
                )
                finish()
            }

        }
    }
    fun tampildataid() {
        tbbrgid = intent.getIntExtra("intent_nis", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.TBBRGDAO().tampilall(tbbrgid).get(0)
            //EtId.setText(barang.id)
            EtNm.setText(barang.nmBrg)
            EtHrg.setText(barang.hrgbrg)
            EtST.setText(barang.stok)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}