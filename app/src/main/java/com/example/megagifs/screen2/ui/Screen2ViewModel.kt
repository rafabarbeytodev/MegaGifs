package com.example.megagifs.screen2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen2
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 12:44
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class Screen2ViewModel : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    private val _enabled = MutableLiveData<Boolean>()
    val enabled: LiveData<Boolean> = _enabled

    private val _text1 = MutableLiveData<String>()
    val text1: LiveData<String> = _text1

    private val _text2 = MutableLiveData<String>()
    val text2: LiveData<String> = _text2

    fun onCounterChange(counter: Int) {
        _counter.value = counter.plus(1)
    }

    fun onEnabledChange(enabled: Boolean) {
        _enabled.value = !enabled
    }

    fun onText1Change(text1: String) {
        _text1.value = text1
    }

    fun onText2Change(text2: String) {
        _text2.value = text2
    }

}