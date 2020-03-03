package com.alwandroid.moviecatalogue.ui.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvShowFragment : Fragment() {

    lateinit var tvShowAdapter: TvShowAdapter
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tvshow, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_tvshow.layoutManager = GridLayoutManager(view.context, 2)
        } else {
            rv_tvshow.layoutManager = GridLayoutManager(view.context, 4)
        }

        rv_tvshow.setHasFixedSize(true)
        val tvshowViewModel = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        tvshowViewModel.setListTVShow()
        tvshowViewModel.getListTvShow().observe(this, Observer<ArrayList<TvShow>> { t: ArrayList<TvShow> ->
            progressBar.visibility = View.GONE
            tvShowAdapter = TvShowAdapter(view.context, t)
            rv_tvshow.adapter = tvShowAdapter
            tvShowAdapter.model = t
        })

    }
}
