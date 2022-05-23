package com.android.marvel.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.android.data.domain.model.CharacterMapper
import com.android.data.util.Constants.Companion.CHARACTER_RESULT_KEY
import com.android.marvel.R
import com.android.marvel.databinding.FragmentCharacterDetailsBinding


class CharacterDetailsFragment : Fragment() {

    private val args by navArgs<CharacterDetailsFragmentArgs>()
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.marvel_characters_details)
        val resultBundle = Bundle()
        resultBundle.putParcelable(CHARACTER_RESULT_KEY, args.result)
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        if(resultBundle !=null) {
            binding.result = resultBundle.getParcelable<CharacterMapper>(CHARACTER_RESULT_KEY) as CharacterMapper
        }
        return binding.root
    }

}