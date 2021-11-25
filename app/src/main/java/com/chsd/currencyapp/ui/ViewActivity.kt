package com.chsd.currencyapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.chsd.currencyapp.R
import com.chsd.currencyapp.adapters.SpinnerAdapter
import com.chsd.currencyapp.databinding.ActivityViewBinding
import com.chsd.currencyapp.db.AppDatabase
import com.chsd.currencyapp.entity.CurrencyData
import com.squareup.picasso.Picasso

class ViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewBinding
    lateinit var db: AppDatabase
    lateinit var dataList: List<CurrencyData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
        window.clearFlags(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        var isChange = false

        val currencyData = intent.getSerializableExtra("data") as CurrencyData
        val position1 = intent.getIntExtra("pos", -1)


        db = AppDatabase.getInstance(this)
        dataList = db.currencyDao().getDataList()
        val spinnerAdapter = SpinnerAdapter(dataList)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner2.adapter = spinnerAdapter
        binding.spinner.setSelection(position1)
        binding.spinner2.setSelection(75)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (currencyData.img != "xd") {
                    Picasso
                        .get()
                        .load("https://flagcdn.com/160x120/${dataList[position].img}.png")
                        .into(binding.image)
                } else binding.image.setImageResource(R.drawable.download)
                val a1 = dataList[position].Rate.toDouble()
                val a2 = dataList[binding.spinner2.selectedItemPosition].Rate.toDouble()
                val i1 = a1 / a2
                binding.title.text = dataList[position].CcyNm_EN
                binding.edit.text = dataList[position].Nominal
                binding.edit2.text = i1.toString().format("%.2f", i1)
                if (isChange) {
                    binding.edit.text = dataList[binding.spinner2.selectedItemPosition].Rate
                    binding.edit2.text = i1.toString().format("%.2f", i1)
                }
                binding.edit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s.toString() != "") {
                            val i = dataList[position].Rate.toDouble()
                            var b = s.toString().toDouble()
                        }

                        binding.edit2.hint = dataList[position].Rate
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })
            }
        }
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Picasso
                    .get()
                    .load("https://flagcdn.com/160x120/${dataList[position].img}.png")
                    .into(binding.image2)
                binding.title2.text = dataList[position].CcyNm_EN
            }
        }

        binding.change.setOnClickListener {
            val pos1 = binding.spinner.selectedItemPosition
            val pos2 = binding.spinner2.selectedItemPosition
            isChange = !isChange
            binding.spinner.setSelection(pos2)
            binding.spinner2.setSelection(pos1)

        }

        buttons()

    }

    @SuppressLint("SetTextI18n")
    private fun buttons() {

        binding.s0.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}0"
        }
        binding.dot.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}."
        }
        binding.s1.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}1"
        }
        binding.s2.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}2"
        }
        binding.s3.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}3"
        }
        binding.s4.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}4"
        }
        binding.s5.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}5"
        }
        binding.s6.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}6"
        }
        binding.s7.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}7"
        }
        binding.s8.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}8"
        }
        binding.s9.setOnClickListener {
            val text = binding.edit.text
            binding.edit.text = "${text}9"
        }
        binding.clear.setOnClickListener {
            if (binding.edit.text.toString() != "") {
                val clear = clear(binding.edit.text.toString())
                binding.edit.text = clear
            }
        }
        binding.c.setOnClickListener {
            binding.edit.text = ""
        }
        binding.check.setOnClickListener {
            val pos1 = binding.spinner.selectedItemPosition
            val pos2 = binding.spinner2.selectedItemPosition
            if (binding.edit.text.toString() != "") {
                val t1 = binding.edit.text.toString().toDouble()
                val a1 = dataList[pos1].Rate.toDouble()
                val a2 = dataList[pos2].Rate.toDouble()
                val i1 = a1 / a2
                var result = t1 * i1
                binding.edit2.text = result.toString()
            }
        }
    }

    fun clear(text: String): String {
        var s = ""
        s = text.substring(0, text.length - 1)
        return s
    }

}