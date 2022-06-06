package com.example.simplenavigationdemowithktx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.simplenavigationdemowithktx.R

class FragmentB : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)
        view.findViewById<Button>(R.id.fragment_jump_to_next_btn)
            .setOnClickListener {
                Navigation.findNavController(view).navigate(NavGraph.Dest.fragment_c)
            }
        return view;
    }
}