package com.example.shakeitup.core.model

import android.util.Log


class ListIngredients(ingredients : HashMap<String, String>) {

    private var listIngredients = convertMeasuresAllList(ingredients)


    fun getlistIngredients() : HashMap<String, String> {
        return listIngredients
    }
    fun changeMeasureValues(measure : String): String {
        var measures : List<String> = measure.split(" ")
        var value : String = measures[0]
        if (value[0].isDigit()) {
            return convertValueInDouble(value).toString() + " " +measures[1]
        }
        return measure
    }

    fun convertMeasuresAllList(ingredients : HashMap<String, String>) : HashMap<String, String> {
        ingredients.forEach{(ingredient, measure) ->
            ingredients[ingredient] = changeMeasureValues(measure)
        }
        return ingredients
    }

    fun convertValueInDouble(value : String): Double {
            if (value.contains('/')){
                var number : List<String> = value.split("/")
                return number[0].toDouble() / number[1].toDouble()
            }
        else if (value.contains('-')){
            var number : List<String> = value.split("-")
            return number[0].toDouble()
        }
        else {
                return value.toDouble()
            }

        }

    fun addTwoMeasures(measure1: String, measure2: String) : String {
        var measures1: List<String> = measure1.split(" ")
        var value1: String = measures1[0]
        var measures2: List<String> = measure2.split(" ")
        var value2: String = measures2[0]
        if (value1[0].isDigit() && value2[0].isDigit()) {
            val newVal1 = convertValueInDouble(value1)
            val newVal2 = convertValueInDouble(value2)
            return (newVal1 + newVal2).toString() + " " + measures1[1]
        } else {
            return measure1
        }
    }

    fun mergeIngredients(newListIngredients : ListIngredients) {
        newListIngredients.listIngredients.forEach { (ingredient, measure) ->
                if (this.listIngredients.isNullOrEmpty()) {
                    this.listIngredients.put(ingredient, changeMeasureValues(measure))
                }
                else if (this.listIngredients.containsKey(ingredient)) {
                    this.listIngredients[ingredient] =
                        this.listIngredients[ingredient]?.let { addTwoMeasures(it, measure) }.toString()
                } else {
                    this.listIngredients.put(ingredient, changeMeasureValues(measure))
                }

            }
        }

    }
