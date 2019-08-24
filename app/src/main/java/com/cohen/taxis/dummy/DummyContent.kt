package com.cohen.taxis.dummy

import com.cohen.taxis.model.Taxi
import java.util.*


object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Taxi> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Taxi> = HashMap()

    val names = arrayListOf(
        "YAFO",
        "HERZL",
        "NAHALAT BINYAMIN",
        "ALLENBY",
        "HA'ALIYA  ",
        "YEHUDA HALEVI",
        "LILIENBLUM",
        "ROTHSHILD",
        "HA-CARMEL",
        "MONTEFIORE",
        "AHAD HA'AM",
        "KALISCHER",
        "GRUZENBERG",
        "MAZEH",
        "RAMBAM",
        "BALFOUR",
        "BRENNER",
        "SHEINKIN",
        "MERKAZ BA'ALEY MALKH",
        "NAPHA",
        "HA'AVODA",
        "BAR GIYORA",
        "BIALIK",
        "HESS",
        "BEN-YEHUDA",
        "BOGRASHOV",
        "HAYARKON"
    )
    private val COUNT = 20

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                createDummyItem(
                    i
                )
            )
        }
    }

    private fun addItem(item: Taxi) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): Taxi {

        return Taxi(
            id = position.toString(),
            title = names[position]
        )
    }

}
