package tk.sh.dev.apps.apps

import android.content.pm.PackageInfo
import androidx.lifecycle.ViewModel
import tk.sh.dev.apps.repository.PackageManagerRepository

class AppListViewModel(private val packageManagerRepository: PackageManagerRepository) : ViewModel() {

    fun getInstalledPackages(): List<PackageInfo> {
        return packageManagerRepository.getList()
    }

}