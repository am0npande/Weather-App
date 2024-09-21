package com.example.weatherappcompose.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappcompose.Models.TopModel
import com.example.weatherappcompose.Models.Weather
import com.example.weatherappcompose.data.weatherAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

class ScreenViewModel : ViewModel() {
    private val _weatherState = MutableStateFlow<TopModel?>(null)
    val weatherState: StateFlow<TopModel?> = _weatherState
    private val weatherapi = weatherAPI.createRetrofitInstance()

    val languagesMap = hashMapOf(
        "Afrikaans" to "af",
        "Albanian" to "sq",
        "Amharic" to "am",
        "Arabic" to "ar",
        "Armenian" to "hy",
        "Azerbaijani" to "az",
        "Basque" to "eu",
        "Belarusian" to "be",
        "Bengali" to "bn",
        "Bosnian" to "bs",
        "Bulgarian" to "bg",
        "Catalan" to "ca",
        "Cebuano" to "ceb",
        "Chinese" to "zh",
        "Corsican" to "co",
        "Croatian" to "hr",
        "Czech" to "cs",
        "Danish" to "da",
        "Dutch" to "nl",
        "English" to "en",
        "Esperanto" to "eo",
        "Estonian" to "et",
        "Finnish" to "fi",
        "French" to "fr",
        "Galician" to "gl",
        "Georgian" to "ka",
        "German" to "de",
        "Greek" to "el",
        "Gujarati" to "gu",
        "Haitian Creole" to "ht",
        "Hausa" to "ha",
        "Hawaiian" to "haw",
        "Hebrew" to "he",
        "Hindi" to "hi",
        "Hmong" to "hmn",
        "Hungarian" to "hu",
        "Icelandic" to "is",
        "Igbo" to "ig",
        "Indonesian" to "id",
        "Irish" to "ga",
        "Italian" to "it",
        "Japanese" to "ja",
        "Javanese" to "jv",
        "Kannada" to "kn",
        "Kazakh" to "kk",
        "Khmer" to "km",
        "Kinyarwanda" to "rw",
        "Korean" to "ko",
        "Kurdish (Kurmanji)" to "ku",
        "Kyrgyz" to "ky",
        "Lao" to "lo",
        "Latin" to "la",
        "Latvian" to "lv",
        "Lithuanian" to "lt",
        "Luxembourgish" to "lb",
        "Macedonian" to "mk",
        "Malagasy" to "mg",
        "Malay" to "ms",
        "Malayalam" to "ml",
        "Maltese" to "mt",
        "Maori" to "mi",
        "Marathi" to "mr",
        "Mongolian" to "mn",
        "Myanmar (Burmese)" to "my",
        "Nepali" to "ne",
        "Norwegian" to "no",
        "Odia (Oriya)" to "or",
        "Pashto" to "ps",
        "Persian" to "fa",
        "Polish" to "pl",
        "Portuguese" to "pt",
        "Punjabi" to "pa",
        "Romanian" to "ro",
        "Russian" to "ru",
        "Samoan" to "sm",
        "Scots Gaelic" to "gd",
        "Serbian" to "sr",
        "Sesotho" to "st",
        "Shona" to "sn",
        "Sindhi" to "sd",
        "Sinhala" to "si",
        "Slovak" to "sk",
        "Slovenian" to "sl",
        "Somali" to "so",
        "Spanish" to "es",
        "Sundanese" to "su",
        "Swahili" to "sw",
        "Swedish" to "sv",
        "Tajik" to "tg",
        "Tamil" to "ta",
        "Tatar" to "tt",
        "Telugu" to "te",
        "Thai" to "th",
        "Turkish" to "tr",
        "Ukrainian" to "uk",
        "Urdu" to "ur",
        "Uyghur" to "ug",
        "Uzbek" to "uz",
        "Vietnamese" to "vi",
        "Welsh" to "cy",
        "Xhosa" to "xh",
        "Yiddish" to "yi",
        "Yoruba" to "yo",
        "Zulu" to "zu"
    )


    fun fetchWeather(city: String, apiKEY: String,language: String = "hi") {
        viewModelScope.launch {
            try {
                val response = weatherapi.getWeather(city, apiKEY, language = language)
                _weatherState.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}