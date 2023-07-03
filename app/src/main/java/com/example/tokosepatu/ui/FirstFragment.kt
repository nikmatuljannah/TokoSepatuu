package com.example.tokosepatu.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokosepatu.R
import com.example.tokosepatu.application.ShoeApp
import com.example.tokosepatu.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val shoeViewModel: ShoeViewModel by viewModels {
        ShoeViewModelFactory((applicationContext as ShoeApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ShoeListAdapter{ shoe ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(shoe)
            findNavController().navigate(action)
        }

        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        shoeViewModel.allShoes.observe(viewLifecycleOwner) { shoes ->
            shoes.let {
                if (shoes.isEmpty()) {
                    binding.EmpityTextView.visibility = View.VISIBLE
                    binding.IlustrationImageView.visibility = View.VISIBLE
                }else{
                    binding.EmpityTextView.visibility = View.GONE
                    binding.IlustrationImageView.visibility = View.GONE
                }

                adapter.submitList(shoes)

            }
        }
        binding.addFAB.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)

       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}