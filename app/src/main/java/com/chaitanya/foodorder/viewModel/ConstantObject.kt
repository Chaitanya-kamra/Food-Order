package com.chaitanya.foodorder.viewModel


object ConstantObject {
        fun getFoodItems(): ArrayList<FoodItem> {
            val foodItems = ArrayList<FoodItem>()

            val names = arrayOf(
                "Eggs Benedict", "Chicken Sandwich", "Pasta Carbonara", "Blueberry Pancakes",
                "Caesar Salad", "Grilled Salmon", "Margherita Pizza", "Vegetable Stir-Fry",
                "French Toast", "Beef Burger", "Spaghetti Bolognese", "Fruit Salad",
                "Waffles", "Club Sandwich", "Chicken Curry","Omelette", "Pancakes with Syrup", "Bacon and Eggs", "Fruit Parfait", "Club Sandwich"
            )

            val descriptions = arrayOf(
                "Delicious eggs benedict with hollandaise sauce",
                "Juicy chicken sandwich with lettuce and tomato",
                "Classic pasta carbonara with creamy sauce",
                "Fluffy blueberry pancakes with maple syrup",
                "Fresh caesar salad with crispy croutons",
                "Healthy grilled salmon with vegetables",
                "Traditional margherita pizza with tomatoes and cheese",
                "Tasty vegetable stir-fry with soy sauce",
                "Sweet French toast with berries",
                "Succulent beef burger with cheese and toppings",
                "Rich spaghetti bolognese with meat sauce",
                "Refreshing fruit salad with assorted fruits",
                "Golden waffles with whipped cream and syrup",
                "Classic club sandwich with turkey and bacon",
                "Spicy chicken curry with rice",
                "Fluffy omelette with cheese and veggies",
                "Pancakes served with sweet syrup",
                "Classic bacon and eggs combo",
                "Delicious fruit parfait with yogurt and granola","Classic bacon and eggs combo",
            )

            val prices = doubleArrayOf(
                9.99, 8.49, 11.99, 7.99, 6.99, 12.99, 10.49, 9.49, 6.49, 8.99, 10.99, 5.99, 7.49, 9.49, 11.49, 8.99, 6.99, 5.99, 4.99, 4.99
            )

            val days = arrayOf(
                "Monday", "Monday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday",
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday",
                "Monday","Monday", "Monday", "Monday", "Monday","Tuesday"
            )

            val meals = arrayOf(
                "Breakfast", "Breakfast", "Dinner", "Breakfast", "Breakfast", "Dinner", "Breakfast", "Lunch",
                "Dinner", "Breakfast", "Lunch", "Dinner", "Breakfast", "Lunch", "Dinner","Breakfast", "Breakfast", "Breakfast", "Breakfast","Lunch"
            )

            for (i in 0 until 20) {
                foodItems.add(
                    FoodItem(
                        names[i], descriptions[i], prices[i], days[i], meals[i]
                    )
                )
            }

            return foodItems
        }
    }


