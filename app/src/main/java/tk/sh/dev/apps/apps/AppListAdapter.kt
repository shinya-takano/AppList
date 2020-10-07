package tk.sh.dev.apps.apps

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.sh.dev.apps.databinding.ItemAppListBinding

class AppListAdapter: RecyclerView.Adapter<AppListAdapter.AppListViewHolder>() {

    private var appList: List<App> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAppListBinding.inflate(inflater, parent, false)
        return AppListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: AppListViewHolder, position: Int) {

        val app = appList[position]
        holder.binding.appName.text = app.appName
        holder.binding.appVersionName.text = app.versionName
    }

    fun updateList(newList: List<App>) {
        appList = newList
        notifyDataSetChanged()
    }

    class AppListViewHolder(var binding: ItemAppListBinding): RecyclerView.ViewHolder(binding.root)
}
data class App(
    val appIcon: Drawable? = null,
    val appName: String? = "",
    val versionName: String? = ""
)