package com.test.gojek

import com.test.gojek.net.model.Repository
import java.util.*


fun mockDummyData(): List<Repository> {
    val dummydata = ArrayList<Repository>()

    for (i in 0..9) {
        val repository = Repository(
            "User $i",
            "Author $i",
            "www.google.com/sample.png $i",
            i * 10L,
            "description $i",
            i * 10L,
            "langauge $i",
            "langaugecolor $i",
            i * 10L,
            i.toString(),
            false
        )
        dummydata.add(repository)
    }
    return dummydata
}
