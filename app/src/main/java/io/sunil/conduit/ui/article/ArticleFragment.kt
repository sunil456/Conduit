package io.sunil.conduit.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.sunil.conduit.R
import io.sunil.conduit.databinding.FragmentArticleBinding
import io.sunil.conduit.extensions.loadImage
import io.sunil.conduit.extensions.timeStamp

class ArticleFragment : Fragment() {

    private var _fragmentArticleBinding : FragmentArticleBinding? = null

    private val articleBinding get() = _fragmentArticleBinding!!

    private lateinit var articleViewModel: ArticleViewModel

    private var articleID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState.let {
            articleID = it?.getString(resources.getString(R.string.arg_article_id))
            Log.d("SUNIL", "onCreate: articleID is $articleID " )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentArticleBinding = FragmentArticleBinding.inflate(inflater, container, false);

        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        arguments.let {
            articleID = it?.getString(resources.getString(R.string.arg_article_id))
            Log.d("SUNIL", "onViewCreated: arguments articleID is $articleID " )
        }

        return articleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleID?.let { articleViewModel.getArticle(it) }

        articleViewModel.article.observe({lifecycle}){
            articleBinding.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it.author.username
                dateTextView.timeStamp = it.createdAt

                avatarImageView.loadImage(it.author.image, true)


            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentArticleBinding = null
    }
}