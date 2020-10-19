package tk.sh.dev.apps.apps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tk.sh.dev.apps.databinding.FragmentAppListBinding
import tk.sh.dev.apps.repository.PackageManagerRepository


class AppListFragment : Fragment(), AppListAdapterListener {

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
            adapter = AppListAdapter(this@AppListFragment)
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
            val app = App(appIcon, appName.toString(), onePackage.versionName, onePackage.packageName)
            list.add(app)
        }
        adapter.updateList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // region implements AppListAdapterListener
    override fun onClickAppSetting(app: App) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.parse("package:${app.packageName}")
        activity?.startActivity(intent)
    }

    override fun onClickNotificationSetting(app: App) {
        val intent = Intent()
        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        intent.data = Uri.parse("package:${app.packageName}")
        activity?.startActivity(intent)
    }

    override fun onClickDeleteStorage(app: App) {
        TODO("Not yet implemented")
    }

    override fun onClickUninstall(app: App) {
        TODO("Not yet implemented")
    }
    // endregion AppListAdapterListener
}