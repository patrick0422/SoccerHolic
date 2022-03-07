package com.example.soccerholic.ui.main.detail

import android.os.Build
import android.text.Html
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.data.search.Response
import com.example.soccerholic.databinding.FragmentDetailBinding
import com.example.soccerholic.ui.main.search.SearchViewModel
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun init() {
        searchViewModel.searchTeamWithTeamId(args.teamId)

        searchViewModel.idSearchResponse.observe(this) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setData(result.data!!.response[0])
                    isLoading(false)
                }
                is NetworkResult.Error -> {
                    makeToast("Error: ${result.message}")
                    isLoading(false)
                }
                is NetworkResult.Loading -> {
                    isLoading(true)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setData(response: Response) = with(binding) {
        Glide
            .with(binding.root)
            .load(response.team.logo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageLogo)

        with(response) {
            textName.text = team.name
            textVenue.text = Html.fromHtml(getString(R.string.venue, venue.name), Html.FROM_HTML_MODE_LEGACY)
            textCountry.text = Html.fromHtml(getString(R.string.country, team.country), Html.FROM_HTML_MODE_LEGACY)
            textFounded.text = Html.fromHtml(getString(R.string.founded, team.founded), Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}