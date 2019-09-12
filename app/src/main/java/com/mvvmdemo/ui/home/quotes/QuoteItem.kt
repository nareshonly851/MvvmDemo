package com.mvvmdemo.ui.home.quotes

import com.mvvmdemo.R
import com.mvvmdemo.data.db.entities.Quote
import com.mvvmdemo.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote: Quote) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout()= R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {

        viewBinding.txtQuotes.text=quote.quote
        viewBinding.txtAuthor.text=quote.author

    }

}