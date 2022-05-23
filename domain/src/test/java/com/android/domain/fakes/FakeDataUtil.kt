package com.android.domain.fakes

import com.android.data.data.dto.Character
import com.android.data.data.dto.Data
import com.android.data.data.dto.Result

object FakeDataUtil {
    object CharacterEntity {
        val character1 = Character(
            code = 200,
            status = "Ok",
            copyright = "© 2022 MARVEL",
            attributionText = "Data provided by Marvel. © 2022 MARVEL",
            attributionHTML = "<a href=\"http://marvel.com\">Data provided by Marvel. © 2022 MARVEL</a>",
            etag = "690cf41b3362c2258a40f3e7a58193b8c071ea23",
            data = Data(
                offset = 0,
                limit = 20,
                total = 1562,
                count = 20,
                results = listOf(
                    Result(
                        id = 1011334,
                        name = "Hulk",
                        description = "",
                        modified = "2014-04-29T14:18:17-0400",
                        comics = null,
                        events = null,
                        resourceURI = "",
                        series = null,
                        stories = null,
                        thumbnail = null,
                        urls = null
                    )
                )
            )
        )

        val character2 = Character(
            code = 200,
            status = "Ok",
            copyright = "© 2022 MARVEL",
            attributionText = "Data provided by Marvel. © 2022 MARVEL",
            attributionHTML = "<a href=\"http://marvel.com\">Data provided by Marvel. © 2022 MARVEL</a>",
            etag = "690cf41b3362c2258a40f3e7a58193b8c071ea23",
            data = Data(
                offset = 0,
                limit = 20,
                total = 1562,
                count = 20,
                results = listOf(
                    Result(
                        id = 1011334,
                        name = "Beast",
                        description = "",
                        modified = "2014-04-29T14:18:17-0400",
                        comics = null,
                        events = null,
                        resourceURI = "",
                        series = null,
                        stories = null,
                        thumbnail = null,
                        urls = null
                    )
                )
            )
        )
    }
}