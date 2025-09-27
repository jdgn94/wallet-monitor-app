package app.jdgn.wallet_monitor.utils

import app.jdgn.wallet_monitor.ui.LocalResource.Images
import org.jetbrains.compose.resources.DrawableResource

val celebrations: List<String> = listOf(
    "birthdayCake", "birthdayCake2", "christmasTree", "christmasWreath", "discoBall", "gift",
    "gift2", "partyHat", "partyPopper"
)

val categoryEmergency: List<String> = listOf(
    "ambulance", "doctor", "doctor2", "fireBrigade", "fireExtinguisher", "police", "siren",
    "stethoscope", "veterinarian"
)

val categoryEntertainment: List<String> = listOf(
    "appleMusic", "appleTv", "cinema", "cinemaScreen", "crunchyroll", "deezer", "epicGames", "gog",
    "headphones", "joystick", "joystick2", "netflix", "nintendo", "playstation", "steam",
    "streamingTv", "soundcloud", "spotify", "theatre", "xbox"
)

val categoryFood: List<String> = listOf(
    "cake", "cakeSlice", "fastFood", "fastFood2", "food", "foodCart", "flash",
    "healthyDrink", "restaurant", "sweets"
)

val categoryPurchases: List<String> = listOf(
    "beautyProduct", "groceryCart", "beer", "diet", "liquor", "petToy", "petFood", "shampoo",
    "waterBottle", "wine"
)

val categoryService: List<String> = listOf(
    "barber", "barberPole", "hair", "hairdresser", "customerService", "customerService2",
    "customerSupport", "carRepair", "delivery", "deliveryBike", "electricalEnergy", "ethernet",
    "ethernet2", "global", "garbageTruck", "lightBulb", "mechanic", "petBeauty", "toolBox",
    "towTruck", "water", "waterRinse", "waterTap", "wifi"
)

val categoryTechnology: List<String> = listOf(
    "pc", "simCard", "smartTv", "smartphone"
)

val categoryTravel: List<String> = listOf(
    "bus", "car", "bike", "cargoBike", "bycicle", "bycicle2", "taxi", "plane", "parking", "beach",
    "tent", "themePark"
)

val categorySchool: List<String> = listOf(
    "graduation", "school", "pen", "pencil", "schoolBus"
)

val categoryExtras: List<String> = listOf(
    "appStore", "appleLogo", "google", "googlePlay", "microsoft",
)

fun imageByString(name: String): DrawableResource{
    var image: DrawableResource
    when (name) {
        "ambulance" -> image = Images.ambulance //
        "appIcon" -> image = Images.appIcon
        "appStore" -> image = Images.appStore //
        "appleLogo" -> image = Images.appleLogo //
        "appleMusic" -> image = Images.appleMusic //
        "appleTv" -> image = Images.appleTv //
        "bank" -> image = Images.bank
        "barber" -> image = Images.barber //
        "barberPole" -> image = Images.barberPole //
        "beach" -> image = Images.beach //
        "beautyProduct" -> image = Images.beautyProduct //
        "beer" -> image = Images.beer //
        "bike" -> image = Images.bike //
        "birthdayCake" -> image = Images.birthdayCake //
        "birthdayCake2" -> image = Images.birthdayCake2 //
        "bus" -> image = Images.bus //
        "bycicle" -> image = Images.bycicle //
        "bycicle2" -> image = Images.bycicle2 //
        "cake" -> image = Images.cake //
        "cakeSlice" -> image = Images.cakeSlice //
        "car" -> image = Images.car //
        "card" -> image = Images.card
        "carRepair" -> image = Images.carRepair //
        "cargoBike" -> image = Images.cargoBike //
        "cash" -> image = Images.cash
        "christmasTree" -> image = Images.christmasTree //
        "christmasWreath" -> image = Images.christmasWreath //
        "cinema" -> image = Images.cinema //
        "cinemaScreen" -> image = Images.cinemaScreen //
        "crunchyroll" -> image = Images.crunchyroll //
        "customerService" -> image = Images.customerService //
        "customerService2" -> image = Images.customerService2 //
        "customerSupport" -> image = Images.customerSupport //
        "deezer" -> image = Images.deezer //
        "deliveryBike" -> image = Images.deliveryBike //
        "delivery" -> image = Images.delivery //
        "diet" -> image = Images.diet //
        "discoBall" -> image = Images.discoBall //
        "doctor" -> image = Images.doctor //
        "doctor2" -> image = Images.doctor2 //
        "electricalEnergy" -> image = Images.electricalEnergy //
        "epicGames" -> image = Images.epicGames //
        "ethernet" -> image = Images.ethernet //
        "ethernet2" -> image = Images.ethernet2 //
        "fastFood" -> image = Images.fastFood //
        "fastFood2" -> image = Images.fastFood2 //
        "fireBrigade" -> image = Images.fireBrigade //
        "fireExtinguisher" -> image = Images.fireExtinguisher //
        "flash" -> image = Images.flash //
        "food" -> image = Images.food //
        "foodCart" -> image = Images.foodCart //
        "garbageTruck" -> image = Images.garbageTruck //
        "genre" -> image = Images.genre
        "gift" -> image = Images.gift //
        "gift2" -> image = Images.gift2 //
        "global" -> image = Images.global //
        "gog" -> image = Images.gog //
        "google" -> image = Images.google //
        "googlePlay" -> image = Images.googlePlay //
        "graduation" -> image = Images.graduation //
        "groceryCart" -> image = Images.groceryCart //
        "hair" -> image = Images.hair //
        "hairdresser" -> image = Images.hairdresser //
        "headphones" -> image = Images.headphones //
        "healthyDrink" -> image = Images.healthyDrink //
        "house" -> image = Images.house
        "joystick" -> image = Images.joystick //
        "joystick2" -> image = Images.joystick2 //
        "judge" -> image = Images.judge
        "judge2" -> image = Images.judge2
        "lightBulb" -> image = Images.lightBulb //
        "liquor" -> image = Images.liquor //
        "marketAnalysis" -> image = Images.marketAnalysis
        "mechanic" -> image = Images.mechanic //
        "microsoft" -> image = Images.microsoft //
        "netflix" -> image = Images.netflix //
        "nintendo" -> image = Images.nintendo //
        "parking" -> image = Images.parking //
        "partyHat" -> image = Images.partyHat //
        "partyPopper" -> image = Images.partyPopper //
        "pc" -> image = Images.pc //
        "pen" -> image = Images.pen //
        "pencil" -> image = Images.pencil //
        "petBeauty" -> image = Images.petBeauty //
        "petFood" -> image = Images.petFood //
        "petToy" -> image = Images.petToy //
        "pieChart" -> image = Images.pieChart
        "piggy" -> image = Images.piggy
        "plane" -> image = Images.plane //
        "playstation" -> image = Images.playstation //
        "police" -> image = Images.police //
        "report" -> image = Images.report
        "restaurant" -> image = Images.restaurant //
        "school" -> image = Images.school //
        "schoolBus" -> image = Images.schoolBus //
        "shampoo" -> image = Images.shampoo //
        "simCard" -> image = Images.simCard //
        "siren" -> image = Images.siren //
        "smartTv" -> image = Images.smartTv //
        "smartphone" -> image = Images.smartphone //
        "soundcloud" -> image = Images.soundcloud //
        "spotify" -> image = Images.spotify //
        "steam" -> image = Images.steam //
        "stethoscope" -> image = Images.stethoscope //
        "streamingTv" -> image = Images.streamingTv //
        "sweets" -> image = Images.sweets //
        "taxi" -> image = Images.taxi //
        "tent" -> image = Images.tent //
        "theatre" -> image = Images.theatre //
        "themePark" -> image = Images.themePark //
        "toolBox" -> image = Images.toolBox //
        "towTruck" -> image = Images.towTruck //
        "veterinarian" -> image = Images.veterinarian //
        "water" -> image = Images.water //
        "waterBottle" -> image = Images.waterBottle //
        "waterRinse" -> image = Images.waterRinse //
        "waterTap" -> image = Images.waterTap //
        "wifi" -> image = Images.wifi //
        "wine" -> image = Images.wine //
        "xbox" -> image = Images.xbox //
        else -> image = Images.notFound
    }

    return image
}
