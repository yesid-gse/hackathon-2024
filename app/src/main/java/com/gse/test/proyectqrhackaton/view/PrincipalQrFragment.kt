package com.gse.test.proyectqrhackaton.view

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.gse.test.proyectqrhackaton.databinding.FragmentPrincipalQrBinding
import com.gse.test.proyectqrhackaton.model.repository.AppDemoRepository
import com.gse.test.proyectqrhackaton.model.request.AppDemoRequest
import com.gse.test.proyectqrhackaton.viewModel.PrincipalQrViewModel
import com.gse.test.proyectqrhackaton.viewModel.factory.PrincipalQrViewModelFactory

class PrincipalQrFragment : Fragment() {

    private lateinit var binding: FragmentPrincipalQrBinding
    private lateinit var codeScanner: CodeScanner

    companion object {
        fun newInstance() = PrincipalQrFragment()
    }

    private lateinit var viewModel: PrincipalQrViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            PrincipalQrViewModelFactory(AppDemoRepository())
        )[PrincipalQrViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrincipalQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
    }

    private fun initUI() {
        codeScanner = CodeScanner(requireActivity(), binding.scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        val handler = Handler(Looper.getMainLooper())
        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            handler.post {
                viewModel.callDemoConsume(requireContext(), AppDemoRequest(urlDemo = it.text))
                Toast.makeText(requireActivity(), "Scan result: ${it.text}", Toast.LENGTH_LONG)
                    .show()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            handler.post {
                Toast.makeText(
                    requireActivity(), "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

}