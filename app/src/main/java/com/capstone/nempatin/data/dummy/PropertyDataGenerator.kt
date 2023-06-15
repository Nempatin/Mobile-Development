package com.capstone.nempatin.data.dummy

import com.capstone.nempatin.domain.Property

class PropertyDataGenerator {
    fun createDummyPropertyList(): List<Property> {
        val propertyList = mutableListOf<Property>()

        propertyList.add(
            Property(
                "Rumah Murah Bekasi",
                "Bantar Gebang, Bekasi",
                -6.223945,
                106.9862751,
                3800000000,
                220,
                220,
                3,
                3,
                "tidak ada",
                "shm - sertifikat hak milik",
                6285410000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Minimalis Baru Jalan 2 Mobil",
                "Bekasi",
                -6.223945,
                106.9862751,
                4500000000,
                250,
                96,
                5,
                4,
                "ada",
                "lainnya (ppjb,girik,adat,dll)",
                6284790000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Rumah Murah Bekasi",
                "Bekasi",
                -6.223945,
                106.9862751,
                3800000000,
                220,
                220,
                3,
                3,
                "tidak ada",
                "shm - sertifikat hak milik",
                6285410000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Minimalis Baru Jalan 2 Mobil",
                "Bekasi",
                -6.223945,
                106.9862751,
                4500000000,
                250,
                96,
                5,
                4,
                "ada",
                "lainnya (ppjb,girik,adat,dll)",
                6284790000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Rumah standard hitung tanah dalam",
                "Bekasi",
                -6.223945,
                106.9862751,
                7500000000,
                150,
                253,
                5,
                2,
                "ada",
                "shm - sertifikat hak milik",
                6286070000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Rumah Mewah Bekasi",
                "Bekasi",
                -6.223945,
                106.9862751,
                6000000000,
                450,
                240,
                9,
                9,
                "tidak ada",
                "shm - sertifikat hak milik",
                6289040000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "RUMAH MURAH BEKASI",
                "Bekasi",
                -6.223945,
                106.9862751,
                8000000000,
                300,
                232,
                6,
                5,
                "tidak ada",
                "lainnya (ppjb,girik,adat,dll)",
                6289660000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "rumah lama layak huni strategis dekat jalan raya",
                "Bekasi",
                -6.223945,
                106.9862751,
                2500000000,
                120,
                90,
                4,
                1,
                "ada",
                "shm - sertifikat hak milik",
                6283980000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Rumah terawat siap huni bagus jalanan dua mobil",
                "Bekasi",
                -6.223945,
                106.9862751,
                5250000000,
                147,
                108,
                4,
                3,
                "ada",
                "lainnya (ppjb,girik,adat,dll)",
                6281600000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Rumah Asri Dan Sangat Strategis",
                "Bekasi",
                -6.223945,
                106.9862751,
                4500000000,
                120,
                145,
                6,
                4,
                "ada",
                "hgb - hak guna bangunan",
                6285040000000,
                generateTimestamp()
            )
        )
        propertyList.add(
            Property(
                "Rumah Baru Murah",
                "Bekasi",
                -6.223945,
                106.9862751,
                3500000000,
                170,
                112,
                4,
                5,
                "ada",
                "hgb - hak guna bangunan",
                6284480000000,
                generateTimestamp()
            )
        )

        propertyList.add(
            Property(
                "Rumah Laventino",
                "Surabaya",
                -7.29294955313054,
                112.75222847533888,
                4500000000,
                120,
                145,
                6,
                4,
                "ada",
                "hgb - hak guna bangunan",
                6285040000000,
                generateTimestamp()

            )
        )


        // Add more properties using the provided data

        return propertyList
    }

    private fun generateTimestamp(): Long {
        return System.currentTimeMillis()
    }
}


