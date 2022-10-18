package com.freeagent.testapp.exchange.viewmodels

import androidx.annotation.LayoutRes
import androidx.databinding.Bindable
import com.freeagent.testapp.R
import java.math.BigDecimal

interface ItemViewModel {
    @get: LayoutRes
    val layoutId: Int
    val viewType: Int
        get() = 0
}

class StandardExchangeItemViewModel(val currency: String,
                                    val value: Double) : ItemViewModel {
    override val layoutId: Int = R.layout.item_view_currency
    override val viewType: Int = RatesListViewModel.STANDARD_ITEM
}

class ComparisonHeaderItemViewModel(val dateTitle: String, val currencyTitle1: String, val currencyTitle2: String) : ItemViewModel {
    override val layoutId: Int = R.layout.item_view_currency
    override val viewType: Int = RatesListViewModel.HEADER_ITEM
}

class ComparisonItemViewModel(val date: String, val currencyVal1: String, val currencyVal2: String) : ItemViewModel {
    override val layoutId: Int = R.layout.item_view_currency
    override val viewType: Int = RatesListViewModel.COMPARISON_ITEM
}