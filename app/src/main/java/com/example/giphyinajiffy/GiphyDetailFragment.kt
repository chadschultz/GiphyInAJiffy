package com.example.giphyinajiffy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.giphyinajiffy.databinding.FragmentGiphyDetailBinding
import com.giphy.sdk.core.models.enums.RenditionType

/**
 * Show a specific GIF full screen, with the title of the GIF in the action bar.
 */
class GiphyDetailFragment : Fragment() {

    private var _binding: FragmentGiphyDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: GiphyDetailFragmentArgs by navArgs()

    private val viewModel: GiphyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGiphyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val media = args.media
        binding.apply {
            media.let { media ->
                mediaView.setMedia(media, RenditionType.original)
            }
        }
        val fallbackTitle = getString(R.string.giphy_detail_fragment_label)
        viewModel.updateActionBarTitle(media.title ?: fallbackTitle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
