package com.bachld.vnexpress

import java.net.HttpURLConnection
import java.net.URL

class XMLParser {

    fun getXmlFromUrl(urlString: String): String {
        var xml = ""
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            // Đọc toàn bộ nội dung XML
            xml = connection.inputStream.bufferedReader().use { it.readText() }

            connection.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return xml
    }
}
