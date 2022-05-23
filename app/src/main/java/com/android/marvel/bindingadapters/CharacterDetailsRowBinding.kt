package com.android.marvel.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.android.marvel.R
import com.android.data.domain.model.CharacterMapper
import org.jsoup.Jsoup

class CharacterDetailsRowBinding  {

    companion object {

        @BindingAdapter("loadImageFromUrlDetails")
        @JvmStatic
        fun loadImageFromUrlDetails(imageView: ImageView, result: CharacterMapper) {
            imageView.load("${result.thumbnail}.${result.thumbnailExt}") {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("parseHtml")
        @JvmStatic
        fun parseHtml(textView: TextView, description: String?){
            if(!description.isNullOrBlank()) {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }else{
                textView.text = "Description not available"
            }
        }

        @BindingAdapter("parseSeries")
        @JvmStatic
        fun parseSeries(textView: TextView, available: Int?){
            if(available?: 0 != 1) {
                textView.text = "${available} Series"
            }else{
                textView.text = "${available} Serie"
            }
        }

        @BindingAdapter("parseStories")
        @JvmStatic
        fun parseStories(textView: TextView, available: Int?){
            if(available?: 0 != 1) {
                textView.text = "${available} Stories"
            }else{
                textView.text = "${available} Story"
            }
        }

        @BindingAdapter("parseComics")
        @JvmStatic
        fun parseComics(textView: TextView, available: Int?){
            if(available?: 0 != 1) {
                textView.text = "${available} Comics"
            }else{
                textView.text = "${available} Comic"
            }
        }

        @BindingAdapter("parseEvents")
        @JvmStatic
        fun parseEvents(textView: TextView, available: Int?){
            if(available?: 0 != 1) {
                textView.text = "${available} Events"
            }else{
                textView.text = "${available} Event"
            }
        }

    }

}