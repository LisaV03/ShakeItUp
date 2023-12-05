package com.example.shakeitup.core.service

import com.example.shakeitup.R
import com.example.shakeitup.core.model.Cocktail

class CocktailsFetcher {

    fun loadCocktails(): List<Cocktail> {
        return listOf<Cocktail>(
            Cocktail(R.string.cocktail1),
            Cocktail(R.string.cocktail2),
            Cocktail(R.string.cocktail3),
            Cocktail(R.string.cocktail4),
            Cocktail(R.string.cocktail5),
            Cocktail(R.string.cocktail6),
            Cocktail(R.string.cocktail7),
            Cocktail(R.string.cocktail8),
            Cocktail(R.string.cocktail9),
            Cocktail(R.string.cocktail10)
        )
    }

}