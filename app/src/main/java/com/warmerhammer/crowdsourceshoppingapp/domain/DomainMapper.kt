package com.warmerhammer.crowdsourceshoppingapp.domain

interface DomainMapper <T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}
