package io.sunil.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.sunil.conduit.R
import io.sunil.conduit.adapter.ArticleFeedAdapter
import io.sunil.conduit.databinding.FragmentFeedBinding


class GlobalFeedFragment: Fragment() {

    private var fragmentFeedBinding: FragmentFeedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val feedBinding get() = fragmentFeedBinding!!

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        fragmentFeedBinding = FragmentFeedBinding.inflate(inflater, container, false)

        feedAdapter = ArticleFeedAdapter{ openArticleFragment(it) }

        return feedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentFeedBinding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        fragmentFeedBinding?.feedRecyclerView?.adapter = feedAdapter
        feedViewModel.getGlobalFeed()

        feedViewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentFeedBinding = null
    }

    private fun openArticleFragment(articleId: String){
        findNavController().navigate(
                R.id.action_globalFeed_OpenArticleFragment,
                bundleOf(
                        resources.getString(R.string.arg_article_id) to articleId
                )
        )
    }
}