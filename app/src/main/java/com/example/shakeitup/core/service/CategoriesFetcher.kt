package com.example.shakeitup.core.service

class CategoriesFetcher : Fetcher() {
    override fun getUrl(): String {
        return "https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list"
    }
}
