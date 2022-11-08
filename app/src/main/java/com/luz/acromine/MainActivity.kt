package com.luz.acromine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.luz.acromine.api.model.Lf
import com.luz.acromine.databinding.ActivityMainBinding
import com.luz.acromine.ui.adapter.AcromineAdapter
import com.luz.acromine.viewmodel.AcromineViewModel
import com.luz.acromine.viewmodel.AcromineViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: AcromineViewModelFactory

    private lateinit var viewModel: AcromineViewModel

    val acronymAdapter by lazy { AcromineAdapter() }
    lateinit var recyclerv: RecyclerView
    lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var actMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        //viewModel = ViewModelProvider(this)[AcromineViewModel::class.java]
        viewModel = ViewModelProvider(this, viewModelFactory).get(AcromineViewModel::class.java)

        actMainBinding.acromineVM = viewModel
        actMainBinding.lifecycleOwner = this

        recyclerv = findViewById(R.id.rvDefinition)
        topAppBar = findViewById(R.id.topAppBar)
        initGridAdapter()

        toolBarItemListener()
        viewModel.livedataAcronym.observe(this, Observer {
            handleResults(it)
        })
        viewModel.error.observe(this, Observer {
            handleErrors(it)
        })
    }
    private fun handleResults(lfList: List<Lf>) {
        acronymAdapter.submitList(lfList)
    }

    private fun initGridAdapter() {
        recyclerv.layoutManager = LinearLayoutManager(this)
        recyclerv.adapter = acronymAdapter
    }

    private fun toolBarItemListener() {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_search -> {
                    val searchView: SearchView =
                        MenuItemCompat.getActionView(menuItem) as SearchView

                    searchView.setOnQueryTextListener(object :
                        SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String): Boolean {
                            acronymAdapter.term = query
                            viewModel.getAcronymList(query)
                            return false
                        }

                        override fun onQueryTextChange(newText: String) = true
                    })

                    true
                }
                else -> false
            }
        }
    }

    private fun handleErrors(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}