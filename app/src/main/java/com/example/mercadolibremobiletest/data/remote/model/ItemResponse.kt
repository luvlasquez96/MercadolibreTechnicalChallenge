package com.example.mercadolibremobiletest.data.remote.model

data class ItemResponse(
    val available_filters: List<AvailableFilter>,
    val available_sorts: List<AvailableSort>,
    val country_default_time_zone: String,
    val filters: List<Any>,
    val paging: Paging,
    val pdp_tracking: PdpTracking,
    val query: String,
    val results: List<Result>,
    val site_id: String,
    val sort: Sort,
    val user_context: Any
)