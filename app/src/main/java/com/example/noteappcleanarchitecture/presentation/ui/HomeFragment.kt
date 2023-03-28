package com.example.noteappcleanarchitecture.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteappcleanarchitecture.R
import com.example.noteappcleanarchitecture.data.entity.NoteEntity
import com.example.noteappcleanarchitecture.data.utils.BUNDLE_ID
import com.example.noteappcleanarchitecture.data.utils.DELETE
import com.example.noteappcleanarchitecture.data.utils.EDIT
import com.example.noteappcleanarchitecture.databinding.FragmentHomeBinding
import com.example.noteappcleanarchitecture.presentation.adapter.NoteAdapter
import com.example.noteappcleanarchitecture.presentation.viewmodel.HomeViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var notesAdapter: NoteAdapter

    @Inject
    lateinit var noteEntity: NoteEntity

    private lateinit var navigationDrawer: ActionBarDrawerToggle
    //Other
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.notesToolbar.title = ""
        //initDrawerLayout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.notesToolbar)
        //InitViews
        binding.apply {
            //Note fragment
            addNoteBtn.setOnClickListener {
                NoteFragment().show(parentFragmentManager, NoteFragment().tag)
            }
        }
        //Get data
        viewModel.getAll()
        lifecycleScope.launchWhenCreated {
            viewModel.getAllNotes.collectLatest {
                if (it != null) {
                    showEmpty(it.isEmpty)
                }
                if (it != null) {
                    it.data?.let { itData ->
                        notesAdapter.setData(itData)
                    }
                }
                binding.noteList.apply {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = notesAdapter
                }
            }
        }

        //search data
        lifecycleScope.launchWhenCreated {
            viewModel.searchNotes.collectLatest {
                if (it != null) {
                    showEmpty(it.isEmpty)
                }
                if (it != null) {
                    it.data?.let { itData ->
                        notesAdapter.setData(itData)
                    }
                }
                binding.noteList.apply {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = notesAdapter
                }
            }
        }

        //Clicks
        notesAdapter.setOnItemClickListener { entity, type ->
            when (type) {
                EDIT -> {
                    val noteFragment = NoteFragment()
                    val bundle = Bundle()
                    bundle.putInt(BUNDLE_ID, entity.id)
                    noteFragment.arguments = bundle
                    noteFragment.show(parentFragmentManager, NoteFragment().tag)
                }
                DELETE -> {
                    noteEntity.id = entity.id
                    noteEntity.title = entity.title
                    noteEntity.disc = entity.disc
                    noteEntity.category = entity.category
                    noteEntity.priority = entity.priority
                    viewModel.deleteNote(noteEntity)
                }
            }
        }
    }

    private fun initDrawerLayout(){
        //drawer = requireActivity().findViewById<View>(R.id.drawerLayout) as AdvanceDrawerLayout

//        binding.menuBtn.setOnClickListener {
//            binding.drawerLayout.openDrawer(GravityCompat.START)
//        }
        navigationDrawer = ActionBarDrawerToggle(
            requireActivity(), binding.drawerLayout, binding.notesToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(navigationDrawer)
        navigationDrawer.syncState()
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)
        binding.drawerLayout.setViewScale(Gravity.START, 0.9f)
        binding.drawerLayout.setViewElevation(Gravity.START, 20f)
    }

    private fun showEmpty(isShown: Boolean) {
        binding.apply {
            if (isShown) {
                emptyLay.visibility = View.VISIBLE
                noteList.visibility = View.GONE
            } else {
                emptyLay.visibility = View.GONE
                noteList.visibility = View.VISIBLE
            }
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        val search = menu.findItem(R.id.actionSearch)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        initDrawerLayout()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchNote(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        if(item.itemId == R.id.profileFragment2){
            //Toast.makeText(requireContext(),"prorile",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment2)
        }
        if(item.itemId == R.id.exit){
            requireActivity().finish()
        }

        if (item.itemId == R.id.settingFragment2){
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment2)
        }
//        binding.navView.setBackgroundColor(resources.getColor(R.color.white))
        return true
    }
}