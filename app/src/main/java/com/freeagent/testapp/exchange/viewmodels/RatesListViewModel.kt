package com.freeagent.testapp.exchange.viewmodels

import android.os.Build
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeagent.testapp.exchange.restclient.FixerApi
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate

class RatesListViewModel : ViewModel() {
    //Todo: Eventually going to have a list of exchange objects from the api
    //AC: [Step 1] "Changing the base currency (EUR) amount re-calculates the exchange amounts for the listed currencies."
    //AC: "The currency rows are selectable. This could be a separate mode that is enabled, for example, by tapping a button."
    //AC: "[Step 2 (see image)] On selection of two rows, it opens a view with the exchange rates of the selected currencies over the last 5 days period."

    private val _baseCurrencyAmount = MutableLiveData<BigDecimal>()
    val baseCurrencyAmount: LiveData<BigDecimal> = _baseCurrencyAmount

    private val _lastValueEntered: String = "0"

    private val today: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now().toString()
    } else { //Test this, e.g. on Android 7.
        "todo"
    }

    private val baseCurrency = "EUR"
    private val testCurrencies = "USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, SEK, NZD"

    //@Bindable (baseObservable?)
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
        viewModelScope.launch {
            val start = today
            val end = today

            val results = FixerApi.retroFitService.getFollowingRatesForTimeseries(start, end, baseCurrency, testCurrencies)
            print(results)
        }
    }
}