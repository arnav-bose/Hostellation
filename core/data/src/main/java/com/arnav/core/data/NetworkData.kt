package com.arnav.core.data

import com.arnav.core.domain.DomainModel

interface NetworkData<D: DomainModel> {
    fun convertToDomainData(): D
}