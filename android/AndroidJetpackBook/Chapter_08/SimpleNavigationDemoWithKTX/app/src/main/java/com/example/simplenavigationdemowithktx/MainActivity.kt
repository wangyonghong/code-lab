package com.example.simplenavigationdemowithktx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.simplenavigationdemowithktx.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_toolbar_fcv)
        val navController = (navHostFragment as NavHostFragment).navController

        navController.graph = navController.createGraph(startDestination = NavGraph.Dest.fragment_a) {
            fragment<FragmentA>(NavGraph.Dest.fragment_a) {
                label = getString(R.string.fragment_a)
                action(NavGraph.Action.to_fragment_b) {
                    destinationId = NavGraph.Dest.fragment_b
                }
            }
            fragment<FragmentB>(NavGraph.Dest.fragment_b) {
                label = getString(R.string.fragment_b)
                action(NavGraph.Action.to_fragment_c) {
                    destinationId = NavGraph.Dest.fragment_c
                }
            }
            fragment<FragmentC>(NavGraph.Dest.fragment_c) {
                label = getString(R.string.fragment_c)
            }
        };

    }
}