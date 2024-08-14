import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentCardBottomBinding
import com.example.test.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

class CardBottomFragment : DialogFragment() {

    private var _binding: FragmentCardBottomBinding? = null
    private val binding get() = _binding!!
//    private val viewModel: DetailViewModel by viewModels<DetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCardBottomBinding.inflate(layoutInflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        observeEvents()
    }

    private fun observeEvents() {
//        with(viewModel) {
//            orderStatus.observe(viewLifecycleOwner) {
//                if (it) {
//                    //  findNavController().navigate(CardBottomFragmentDirections.actionCardBottomFragmentToSuccesFragment())
//                } else {
//                    Toast.makeText(context, "Bİlinmeyen bir hata oluştu", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}