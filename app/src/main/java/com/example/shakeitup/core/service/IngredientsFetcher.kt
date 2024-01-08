package com.example.shakeitup.core.service

class IngredientsFetcher : Fetcher() {
    override fun getUrl(): String {
        return "https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list"
    }
}
