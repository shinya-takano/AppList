package tk.sh.dev.apps.apps

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.sh.dev.apps.databinding.ItemAppListBinding

class AppListAdapter(private val listener: AppListAdapterListener): RecyclerView.Adapter<AppListAdapter.AppListViewHolder>() {

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
        holder.binding.appIcon.setImageDrawable(app.pi!!.applicationInfo.loadIcon(holder.binding.root.context.applicationContext.packageManager))
        val descriptionBuilder = StringBuilder()
        descriptionBuilder.append(app.pi.applicationInfo.loadLabel(holder.binding.root.context.applicationContext.packageManager)).append("\n")
        descriptionBuilder.append(app.versionName)
        holder.binding.appDescriptions.text = descriptionBuilder.toString()

        holder.binding.button1.setOnClickListener {
            listener.onClickAppSetting(app)
        }
        holder.binding.button2.setOnClickListener {
            listener.onClickNotificationSetting(app)
        }
        holder.binding.button3.setOnClickListener {
            listener.onClickDeleteStorage(app)
        }
        holder.binding.button4.setOnClickListener {
            listener.onClickUninstall(app)
        }
    }

    fun updateList(newList: List<App>) {
        appList = newList
        notifyDataSetChanged()
    }

    class AppListViewHolder(var binding: ItemAppListBinding): RecyclerView.ViewHolder(binding.root)
}
data class App(
        val pi: PackageInfo? = null,
    val appIcon: Drawable? = null,
    val appName: String? = "",
    val versionName: String? = "",
    val packageName: String = ""
)
interface AppListAdapterListener {
    fun onClickAppSetting(app: App)
    fun onClickNotificationSetting(app: App)
    fun onClickDeleteStorage(app: App)
    fun onClickUninstall(app: App)
}