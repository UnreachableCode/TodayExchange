package com.freeagent.testapp.exchange.viewmodels

import android.content.ClipData.Item
import android.os.Build
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeagent.testapp.exchange.restclient.FixerApi
import com.freeagent.testapp.exchange.restclient.FixerDateRates
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDate

class RatesListViewModel : ViewModel() {
    //AC: [Step 1] "Changing the base currency (EUR) amount re-calculates the exchange amounts for the listed currencies."
    //AC: "The currency rows are selectable. This could be a separate mode that is enabled, for example, by tapping a button."
    //AC: "[Step 2 (see image)] On selection of two rows, it opens a view with the exchange rates of the selected currencies over the last 5 days period."

    private val _baseCurrencyAmount = MutableLiveData<Double>()
    val baseCurrencyAmount: LiveData<Double> = _baseCurrencyAmount

    private val _lastValueEntered: String = "0"

    private val df = DecimalFormat("0.00")

    private val today: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now().toString()
    } else { //Test this, e.g. on Android 7.
        "todo"
    }

    private val baseCurrency = "EUR"
    private val testCurrencies = "USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, SEK, NZD"

    private lateinit var cachedResults: FixerDateRates

    val viewData: LiveData<List<ItemViewModel>>
        get() = _viewData
    private val _viewData = MutableLiveData<List<ItemViewModel>>(emptyList())

    /*
    @Bindable
    fun getCurrencyAmountText(): String {
        return _baseCurrencyAmount.toString() //todo: Check how to format this to a scale:2 string for the UI.
    }

    fun setCurrencyAmountText(value: String) {
        if (value != _lastValueEntered) {
            //Parse this value string into a BigDecimal
            //_baseCurrencyAmount.postValue(Double(value))

            //todo: what are we notifying? notifyPropertyChanged(BR.currency_amount_text);
        }
    }
    */

    init {
        val fixerDateRates = getExchangeRatesForCurrentDate()
    }

    private fun cacheCurrencyRates(fixerDateRates: FixerDateRates){

    }

    private fun formatStandardModeViewData(fixerDateRates: FixerDateRates): List<ItemViewModel> {
        val normalViewData = mutableListOf<ItemViewModel>()
        cachedResults?.rates.forEach { (key, value) ->
            normalViewData.add(StandardExchangeItemViewModel(key, df.format(value*100)))
        }
        val cachedRatesMap = cachedResults?.rates
        return normalViewData;
    }


    //Ambiguity - is this one API call that is processed, or an API call made as the user types? Latter seems sleeker.
    //AC: fetch only these currencies: " USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, SEK, NZD. All exchange rates should use EUR as a base currency."
    private fun getExchangeRatesForCurrentDate() {
        viewModelScope.launch {
            try {
                val date = today
                cachedResults = FixerApi.retroFitService.getFollowingRatesForDate(date, baseCurrency, testCurrencies)

                val normalModeViewData = formatStandardModeViewData(cachedResults)

                _viewData.postValue(normalModeViewData)

            } catch (e: Exception) {
                //We will display a network error banner this way.
                print(e.message)
            }
            //todo: Remember to catch a timeout
        }
    }

    companion object {
        const val STANDARD_ITEM = 0
        const val HEADER_ITEM = 1
        const val COMPARISON_ITEM = 2
    }
}
