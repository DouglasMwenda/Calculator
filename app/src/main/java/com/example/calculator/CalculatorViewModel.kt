package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel : ViewModel() {

    private val _expression = MutableLiveData<String>()
    val expression: LiveData<String> get() = _expression

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    private var canAddOperation = false
    private var canAddDecimal = true

    init {
        _expression.value = ""
        _result.value = ""
    }

    fun appendNumber(number: String) {
        _expression.value += number
        canAddOperation = true
    }

    fun appendDot() {
        if (canAddDecimal) {
            _expression.value += "."
            canAddDecimal = false
        }
    }

    fun appendOperator(operator: String) {
        if (canAddOperation) {
            _expression.value += operator
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun clear() {
        _expression.value = ""
        _result.value = ""
        canAddOperation = false
        canAddDecimal = true
    }

    fun backspace() {
        _expression.value = _expression.value?.dropLast(1)
        if (_expression.value?.lastOrNull() == '.') {
            canAddDecimal = true
        }
        canAddOperation = _expression.value?.lastOrNull()?.isDigit() == true
    }

    fun calculateResult() {
        try {
            val expression = ExpressionBuilder(_expression.value).build()
            val result = expression.evaluate()
            _result.value = result.toString()
        } catch (e: Exception) {
            _result.value = "Error"
        }
    }
}
