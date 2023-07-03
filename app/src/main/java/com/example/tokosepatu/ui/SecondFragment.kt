package com.example.tokosepatu.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tokosepatu.R
import com.example.tokosepatu.application.ShoeApp
import com.example.tokosepatu.databinding.FragmentSecondBinding
import com.example.tokosepatu.model.Shoe

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val ShoeViewModel: ShoeViewModel by viewModels {
        ShoeViewModelFactory((applicationContext as ShoeApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var shoe: Shoe? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoe = args.shoe
        if (shoe != null) {
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "ubah"
            binding.nameEditText.setText(shoe?.name)
            binding.addresEditText.setText(shoe?.address)
            binding.ownerEditText.setText(shoe?.owner)
        }
        val name = binding.nameEditText.text
        val address = binding.addresEditText.text
        val owner = binding.ownerEditText.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty()) {
                Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()) {
                Toast.makeText(context, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (owner.isEmpty()) {
                Toast.makeText(context, "Nama Pemilik tidak boleh kosong", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (shoe == null) {
                    val Shoe = Shoe(0, name.toString(), address.toString(), owner.toString())
                    ShoeViewModel.insert(Shoe)
                } else {
                    val Shoe =
                        Shoe(shoe?.id!!, name.toString(), address.toString(), owner.toString())
                    ShoeViewModel.update(Shoe)
                }

                findNavController().popBackStack()
            }

            }
            binding.deleteButton.setOnClickListener {
                shoe?.let { ShoeViewModel.delete(it) }
                findNavController().popBackStack()

            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }