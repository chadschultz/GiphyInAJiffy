package com.example.giphyinajiffy

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.giphyinajiffy.databinding.FragmentGiphyListBinding
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.ui.pagination.GPHContent
import com.giphy.sdk.ui.views.GPHGridCallback
import timber.log.Timber

/**
 * List trending GIFs.
 *
 * Searches for GIFs as the user types.
 */
class GiphyListFragment : Fragment() {

    private var _binding: FragmentGiphyListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGiphyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTrendingQuery()

        binding.gifsGridView.callback = object : GPHGridCallback {
            override fun contentDidUpdate(resultCount: Int) {
                Timber.d("total GIFs loaded for search now %d", resultCount)
            }

            override fun didSelectMedia(media: Media) {
                Timber.i("Gif ${media.id} with title \"${media.title}\" selected.")
                val action = GiphyListFragmentDirections.actionFirstFragmentToSecondFragment(media)
                findNavController().navigate(action)
            }
        }

        binding.searchButton.setOnClickListener {
            dismissKeyboard()
            performCustomSearch()
        }

        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_GO) {
                dismissKeyboard()
                performCustomSearch()
                return@setOnEditorActionListener true
            }
            false
        }

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = Unit

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                performCustomSearch()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun performCustomSearch() {
        if (binding.searchInput.text.isNullOrEmpty())
            setTrendingQuery()
        else
            binding.gifsGridView.content =
                GPHContent.searchQuery(binding.searchInput.text.toString(), MediaType.gif)
    }

    private fun dismissKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchInput.windowToken, 0)
    }

    private fun setTrendingQuery() {
        binding.gifsGridView.content = GPHContent.trendingGifs
    }
}
