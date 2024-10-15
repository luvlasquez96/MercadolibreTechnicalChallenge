package com.example.mercadolibremobiletest.data.remote.mapper

import com.example.mercadolibremobiletest.data.remote.model.CategoryDetailsResponse
import com.example.mercadolibremobiletest.data.remote.model.PathFromRootResponse
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.domain.model.PathFromRoot

fun CategoryDetailsResponse.toCategoryDetails() : CategoryDetails{
    return CategoryDetails(
        attributable = attributable,
        id = id,
        name = name,
        pathFromRootResponse = this.pathFromRootResponse.map { it.toPathFromRoot() },
        permalink = permalink,
        picture = picture,
        totalItemsInThisCategory = totalItemsInThisCategory,
        attributeTypes = attributeTypes
        )
}

fun CategoriesItem.toCategoryDetails() : CategoryDetails{
    return CategoryDetails(
        id = id,
        name = name,
        totalItemsInThisCategory = 0,
        attributeTypes = "",
        pathFromRootResponse = emptyList(),
        permalink = "",
        picture = "",
        attributable = false
    )
}

fun PathFromRootResponse.toPathFromRoot() : PathFromRoot{
    return PathFromRoot(id, name)
}
