package com.example.chiacademytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chiacademytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.let { count = savedInstanceState.getInt(BUNDLE_KEY_COUNT) }
        binding.tvCount.text = count.toString()
        binding.btIncr.setOnClickListener { increment() }
    }

    private fun increment() {
        count++
        binding.tvCount.text = count.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BUNDLE_KEY_COUNT, count)
    }

    companion object {
        private const val BUNDLE_KEY_COUNT = "bundle_key_count"
    }
}