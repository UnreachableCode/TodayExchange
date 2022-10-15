package com.freeagent.testapp.exchange.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

class RatesListViewModel : ViewModel() {
    //Todo: Eventually going to have a list of exchange objects from the api
    //AC: [Step 1] "Changing the base currency (EUR) amount re-calculates the exchange amounts for the listed currencies."
    //AC: "The currency rows are selectable. This could be a separate mode that is enabled, for example, by tapping a button."
    //AC: "[Step 2 (see image)] On selection of two rows, it opens a view with the exchange rates of the selected currencies over the last 5 days period."

    private val _baseCurrencyAmount = MutableLiveData<BigDecimal>()
    val baseCurrencyAmount: LiveData<BigDecimal> = _baseCurrencyAmount

    private val _lastValueEntered: String = "0"

    @Bindable
    fun getCurrencyAmountText(): String {
        return _baseCurrencyAmount.toString() //todo: Check how to format this to a scale:2 string for the UI.
    }

    fun setCurrencyAmountText(value: String) {
        if (value != _lastValueEntered) {
            //Parse this value string into a BigDecimal
            _baseCurrencyAmount.postValue(BigDecimal(value))

            //todo: what are we notifying? notifyPropertyChanged(BR.currency_amount_text);
        }
    }

    init {
        getExchangeRatesForCurrentDate()
    }


    //Ambiguity - is this one API call that is processed, or an API call made as the user types? Latter seems sleeker.
    //AC: fetch only these currencies: " USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, SEK, NZD. All exchange rates should use EUR as a base currency."
    private fun getExchangeRatesForCurrentDate() {

    }
}