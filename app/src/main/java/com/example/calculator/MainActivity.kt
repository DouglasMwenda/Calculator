package com.example.calculator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.expression.observe(this) { expression ->
            binding.tvExpression.text = expression
        }

        viewModel.result.observe(this) { result ->
            binding.tvResult.text = result
        }

        setNumberButtonListeners()
        setActionButtonListeners()
    }

    private fun setNumberButtonListeners() {
        binding.zeroTv.setOnClickListener { viewModel.appendNumber("0") }
        binding.oneTv.setOnClickListener { viewModel.appendNumber("1") }
        binding.twoTv.setOnClickListener { viewModel.appendNumber("2") }
        binding.threeTv.setOnClickListener { viewModel.appendNumber("3") }
        binding.fourTv.setOnClickListener { viewModel.appendNumber("4") }
        binding.fiveTv.setOnClickListener { viewModel.appendNumber("5") }
        binding.sixTv.setOnClickListener { viewModel.appendNumber("6") }
        binding.sevenTv.setOnClickListener { viewModel.appendNumber("7") }
        binding.eightTv.setOnClickListener { viewModel.appendNumber("8") }
        binding.nineTv.setOnClickListener { viewModel.appendNumber("9") }
        binding.dotTv.setOnClickListener { viewModel.appendDot() }
    }

    private fun setActionButtonListeners() {
        binding.cleaTv.setOnClickListener { viewModel.clear() }
        binding.backTv.setOnClickListener { viewModel.backspace() }
        binding.plusTv.setOnClickListener { viewModel.appendOperator("+") }
        binding.minusTv.setOnClickListener { viewModel.appendOperator("-") }
        binding.mulTv.setOnClickListener { viewModel.appendOperator("*") }
        binding.divTv.setOnClickListener { viewModel.appendOperator("/") }
        binding.equalTv.setOnClickListener { viewModel.calculateResult() }
    }
}
