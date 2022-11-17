package com.fberg.newsapp.common

object Constants {

    private const val API_VERSION = "v2"
    const val API_KEY = "98ad516f84bd490e8061feacb43979aa"
    // "98ad516f84bd490e8061feacb43979aa" // given
    // "5db2f7eae6e2483494f375d18c279349"
    // "66931071762e4b7b915f0eff7e576ba1"
    // "4d65898b0457485f9edd706d625b95b1" gg
    const val BASE_URL = "https://newsapi.org/$API_VERSION/"

    const val DEFAULT_PAGE_SIZE = 10
    const val CATEGORY_PARAM = "category_param"
    const val URL_PARAM = "url_param"
}
