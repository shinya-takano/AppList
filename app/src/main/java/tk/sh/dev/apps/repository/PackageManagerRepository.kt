package tk.sh.dev.apps.repository

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

class PackageManagerRepository(private val applicationContext: Context) {

    fun getList(): List<PackageInfo> {
        return applicationContext.packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
    }


}