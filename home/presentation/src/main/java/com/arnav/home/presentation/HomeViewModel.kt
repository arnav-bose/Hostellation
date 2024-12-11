package com.arnav.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnav.core.domain.Result
import com.arnav.core.domain.ScreenOverlayData
import com.arnav.home.domain.property.HomeRepository
import com.arnav.home.domain.property.PropertyCardModel
import com.arnav.home.domain.property.currency.CurrencyRateMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _propertyListStateFlow = MutableStateFlow<List<PropertyCardModel>>(emptyList())
    val propertyList = _propertyListStateFlow
        .onStart {
            viewModelScope.launch(Dispatchers.IO) {
                getPropertyList()
                getCurrencyRates()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )

    var currencyRateMap: CurrencyRateMap? = null

    private val _overlayStateFlow = MutableStateFlow<ScreenOverlayData>(ScreenOverlayData.Loading)
    val overlayStateFlow = _overlayStateFlow.asStateFlow()

    private val _selectedPropertyModel = MutableStateFlow<PropertyCardModel?>(null)
    val selectedPropertyModel = _selectedPropertyModel.asStateFlow()

    private suspend fun getPropertyList() {
        _overlayStateFlow.update { ScreenOverlayData.Loading }
        when (val result = homeRepository.getPropertyDetails()) {
            is Result.Error -> {
                _overlayStateFlow.update { ScreenOverlayData.Error(result.error) }
            }

            is Result.Success -> {
                _overlayStateFlow.update { ScreenOverlayData.None }
                _propertyListStateFlow.update { result.data.propertyList }
            }
        }
    }

    private suspend fun getCurrencyRates() {
        when (val result = homeRepository.getCurrencyRates()) {
            is Result.Error -> {
                // no-op
            }
            is Result.Success -> {
                currencyRateMap = result.data
            }
        }
    }

    fun setSelectedProperty(propertyCardModel: PropertyCardModel?) {
        _selectedPropertyModel.update { propertyCardModel }
    }
}
