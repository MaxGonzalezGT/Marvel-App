package com.android.marvel.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.android.data.data.dto.State
import com.android.marvel.R
import org.jsoup.Jsoup
import java.lang.Exception
import com.android.data.domain.model.CharacterMapper
import com.android.data.util.Constants.Companion.CHARACTER_NOT_FOUND
import com.android.marvel.ui.fragments.MarvelCharactersFragmentDirections


class CharacterRowBinding {

    companion object {

        @BindingAdapter("onCharacterClickListener")
        @JvmStatic
        fun onCharacterClickListener(charapterRowLayout: ConstraintLayout, result: CharacterMapper) {
            Log.d("onCharapterClickListener", "CALLED")
            charapterRowLayout.setOnClickListener {
                try {
                    val action = MarvelCharactersFragmentDirections.actionMarvelCharactersFragmentToCharacterDetailsFragment(result)
                    charapterRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onCharapterClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, result: CharacterMapper) {
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

        @BindingAdapter("readApiResponse")
        @JvmStatic
        fun readApiResponse(
            view: View,
            apiResponse: State?
        ){
            when (view){
                is ImageView ->{
                    if(apiResponse != null)
                    view.isVisible = apiResponse.error.isNotBlank() && apiResponse?.error.toString() != CHARACTER_NOT_FOUND
                }
                is TextView ->{
                    if(apiResponse != null) {
                        view.isVisible = apiResponse.error.isNotBlank()
                        view.text = apiResponse.error.toString()
                    }
                }
            }
        }

    }

}