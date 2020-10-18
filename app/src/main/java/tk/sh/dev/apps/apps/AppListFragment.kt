package tk.sh.dev.apps.apps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tk.sh.dev.apps.databinding.FragmentAppListBinding
import tk.sh.dev.apps.repository.PackageManagerRepository


class AppListFragment : Fragment() {

    private var _binding: FragmentAppListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AppListViewModel

    private lateinit var adapter: AppListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = AppListAdapter()
        }
        adapter = binding.recyclerView.adapter as AppListAdapter

        viewModel = AppListViewModel(PackageManagerRepository(requireContext().applicationContext))
        val packages = viewModel.getInstalledPackages()

        val list = arrayListOf<App>()
        for (onePackage in packages) {
            val appName = onePackage.applicationInfo.loadLabel(requireContext().applicationContext.packageManager)
            val appIcon = onePackage.applicationInfo.loadIcon(requireContext().applicationContext.packageManager)
            if (appName == onePackage.packageName) {
                continue
            }
            val app = App(appIcon, appName.toString(), onePackage.versionName)
            list.add(app)
        }
        adapter.updateList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}