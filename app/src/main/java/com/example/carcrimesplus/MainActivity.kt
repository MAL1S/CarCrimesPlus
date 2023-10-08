package com.example.carcrimesplus

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.carcrimesplus.databinding.ActivityMainBinding
import com.example.carcrimesplus.navigation.NavigationEvent
import com.example.carcrimesplus.navigation.coroutineScope
import com.example.carcrimesplus.navigation.navigationObserver
import com.example.carcrimesplus.rv.base.RecyclerDelegateAdapter
import com.example.carcrimesplus.rv.navdrawer.delegate.GroupDelegate
import com.example.carcrimesplus.rv.navdrawer.delegate.ScreenItemDelegate
import com.example.carcrimesplus.rv.navdrawer.delegate.SubScreenItemDelegate
import com.example.carcrimesplus.rv.navdrawer.items.GroupNavItem
import com.example.carcrimesplus.rv.navdrawer.items.ScreenNavItem
import com.example.carcrimesplus.rv.navdrawer.items.SubScreenNavItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val adapter = RecyclerDelegateAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        setup()
        fillDelegates()
        setupNavItems()
        setupStartPage()

        val animationDrawable = binding.navHeader.header.background as AnimationDrawable
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(2000)
            start()
        }

        binding.toolbar.title = "Оборот оружия"

        binding.fab.setOnClickListener { view ->
            val clickAction = View.OnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:" + "stolna_85@mail.ru")
                startActivity(intent)
            }
            Snackbar.make(view, "e-mail: stolna_85@mail.ru", Snackbar.LENGTH_LONG)
                .setAction("Написать", clickAction)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.blue))
                .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                .show()
        }
    }

    override fun onResume() {
        super.onResume()

        setupNavigationCollector()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setup() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            binding.drawer
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        binding.toolbar.elevation = 0f
        binding.appBarLayout.elevation = 0f
    }

    private fun fillDelegates() {
        val screenDelegate = ScreenItemDelegate { position ->
            adapter.items.forEachIndexed { index, item ->
                (adapter.items[index] as? ScreenNavItem)?.let {
                    it.selected = index == position
                    binding.rcv.adapter?.notifyDataSetChanged()
                    return@forEachIndexed
                }
                (adapter.items[index] as? SubScreenNavItem)?.let {
                    it.selected = false
                }
                binding.rcv.adapter?.notifyDataSetChanged()
            }
        }

        val subScreenDelegate = SubScreenItemDelegate { position ->
            adapter.items.forEachIndexed { index, item ->
                (adapter.items[index] as? SubScreenNavItem)?.let {
                    it.selected = index == position
                    binding.rcv.adapter?.notifyDataSetChanged()
                    return@forEachIndexed
                }
                (adapter.items[index] as? ScreenNavItem)?.let {
                    it.selected = false
                }
                binding.rcv.adapter?.notifyDataSetChanged()
            }
        }

        val groupDelegate = GroupDelegate { shouldExpand, position, items ->
            if (shouldExpand) {
                adapter.items.addAll(position+1, items)
            } else {
                adapter.items.removeAll(items)
            }
            (adapter.items[position] as GroupNavItem).selected = shouldExpand
            binding.rcv.adapter?.notifyDataSetChanged()
        }

        adapter.addDelegate(screenDelegate)
        adapter.addDelegate(subScreenDelegate)
        adapter.addDelegate(groupDelegate)
    }

    private fun setupNavItems() {
        adapter.items = with(ContentInflater(this)) {
            mutableListOf(
                inflateDescription(),
                inflateWeapon(),
                inflateCriminalLaw(),
                inflateCriminalCharacteristics(),
                inflateTrialPractice(),
                inflateCriminalHelpInfo(),
                inflateAuthors()
            )
        }

        binding.rcv.adapter = adapter
        binding.rcv.adapter?.notifyDataSetChanged()
    }

    private fun setupNavigationCollector() {
        coroutineScope.launch {
            navigationObserver.navFlow.collect {
                withContext(Dispatchers.Main) {
                    binding.drawer.close()
                    binding.toolbar.title = it.title
                }
            }
        }
    }

    private fun setupStartPage() {
        coroutineScope.launch(Dispatchers.Main) {
            val navEvent = ContentInflater(this@MainActivity).inflateDescription()
            navigationObserver.sendNavigationEvent(
                NavigationEvent(0, navEvent.name, navEvent.content)
            )
        }
        binding.drawer.open()
        (adapter.items[0] as ScreenNavItem).selected = true
        binding.rcv.adapter?.notifyDataSetChanged()
    }
}